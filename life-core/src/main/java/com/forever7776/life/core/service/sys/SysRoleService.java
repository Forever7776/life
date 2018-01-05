package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.SysRole;
import com.forever7776.life.core.persistence.sys.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色
 *
 * @author kz
 * @date 2017-12-21
 */
@Service
@Transactional
public class SysRoleService {

    @Autowired(required = false)
    private SysRoleMapper sysRoleMapper;

    public void save(SysRole sysRole) {
        sysRoleMapper.insertSelective(sysRole);
    }
}