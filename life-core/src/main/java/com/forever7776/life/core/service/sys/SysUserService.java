package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.SysUser;
import com.forever7776.life.core.entity.sys.SysUserExample;
import com.forever7776.life.core.persistence.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 0
 * @date 2017-12-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserService {

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户登录账户昵称查找
     *
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    public SysUser findByNickName(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据邮箱查找用户
     *
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public Integer querySysUserByEmail(String email) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andEmailEqualTo(email);
        return sysUserMapper.countByExample(example);
    }

    @Transactional(readOnly = true)
    @Cacheable
    public SysUser selectOne(String username, String pwd) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(pwd);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public void save(SysUser user) {
        sysUserMapper.insertSelective(user);
    }

    public void update(SysUser user) {
        sysUserMapper.updateByPrimaryKey(user);
    }

    /**
     * 更新用户登录时间
     *
     * @param username
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateLoginTime(String username) {
        sysUserMapper.updateLoginTime(username);
    }

    /**
     * 更新用户登出时间
     *
     * @param username
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateLogoutTime(String username) {
        sysUserMapper.updateLogoutTime(username);
    }
}