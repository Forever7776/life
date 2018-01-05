package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.SysRoleUser;
import com.forever7776.life.core.persistence.sys.SysRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色-用户
 *
 * @author kz
 * @date 2017-12-21
 */
@Service
@Transactional
public class SysRoleUserService {

    @Autowired(required = false)
    private SysRoleUserMapper sysRoleUserMapper;

    public void save(SysRoleUser sysRoleUser) {
        sysRoleUserMapper.insertSelective(sysRoleUser);
    }

    /**
     * 根据用户ID,查询角色ID
     *
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public String getRoleIdByUserId(String userId) {
        return sysRoleUserMapper.getRoleIdByUserId(userId);
    }
}
