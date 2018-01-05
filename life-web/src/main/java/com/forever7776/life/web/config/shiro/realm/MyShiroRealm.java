package com.forever7776.life.web.config.shiro.realm;

import com.forever7776.life.core.entity.sys.SysUser;
import com.forever7776.life.core.service.sys.SysRoleFunctionService;
import com.forever7776.life.core.service.sys.SysRoleUserService;
import com.forever7776.life.core.service.sys.SysUserService;
import com.forever7776.life.remote.common.vo.SysRoleFunctionVO;
import com.forever7776.life.remote.rpc.SendEmailRpcService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import util.ConfigTool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * shiro身份校验核心类
 *
 * @author kz
 * @date 2018年1月1日 下午3:19:48
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysRoleFunctionService sysRoleFunctionService;

    @Autowired(required = false)
    private SendEmailRpcService sendEmailRpcService;

    /**
     * 管理者邮箱
     */
    private String managerEmail = ConfigTool.getProp("manager.email");
    /**
     * 用户登录次数计数  redisKey 前缀
     */
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    /**
     * 用户登录是否被锁定    一小时 redisKey 前缀
     */
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 错误登录次数
     */
    private static final int MAX_LOGIN_NUM = 5;

    /**
     * 错误次数大于5次,加锁标志
     */
    private static final String LOGIN_LOCK_NAME = "LOCK";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        boolean redisConnect = false;
        try {
            opsForValue.increment(SHIRO_LOGIN_COUNT + name, 1);
            redisConnect = true;
            //计数大于5时，设置用户被锁定一小时
            if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + name)) >= MAX_LOGIN_NUM) {
                opsForValue.set(SHIRO_IS_LOCK + name, LOGIN_LOCK_NAME);
                stringRedisTemplate.expire(SHIRO_IS_LOCK + name, 1, TimeUnit.HOURS);
            }
            if (LOGIN_LOCK_NAME.equals(opsForValue.get(SHIRO_IS_LOCK + name))) {
                throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
            }
        } catch (Exception e) {
            logger.warn("################redis没有启动#################");
            sendEmailRpcService.sendSimpleMail(managerEmail, "系统提示", "redis没有启动");
        }
        //md5加密
        String paw = DigestUtils.md5Hex(password);
        SysUser user = sysUserService.selectOne(name, paw);
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if (user.getStatus() != 0) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        } else {
            //登录成功,更新登录时间
            sysUserService.updateLoginTime(name);
            //清空登录计数
            if (redisConnect) {
                opsForValue.set(SHIRO_LOGIN_COUNT + name, "0");
            }
        }
        logger.info("身份认证成功，登录用户:{}", name);

        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        String roleId = sysRoleUserService.getRoleIdByUserId(userId);
        Set<String> roleSet = new HashSet<String>();
        roleSet.add(roleId);

        //根据用户ID查询权限（permission），放入到Authorization里。
        List<SysRoleFunctionVO> permissionList = sysRoleFunctionService.queryRoleFunctionList(roleId);
        Set<String> permissionSet = new HashSet<String>();
        for (SysRoleFunctionVO functionVO : permissionList) {
            permissionSet.add(functionVO.getFunctionUrl());
        }
        info.setStringPermissions(permissionSet);
        return info;
    }

}
