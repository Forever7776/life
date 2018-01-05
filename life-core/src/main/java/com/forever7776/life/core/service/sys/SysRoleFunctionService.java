package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.SysRoleFunction;
import com.forever7776.life.core.persistence.sys.SysRoleFunctionMapper;
import com.forever7776.life.remote.common.vo.SysRoleFunctionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色-功能
 *
 * @author 0
 * @date 2017-12-21
 */
@Service
@Transactional
public class SysRoleFunctionService {

    @Autowired(required = false)
    private SysRoleFunctionMapper sysRoleFunctionMapper;

    public void save(SysRoleFunction sysRoleFunction){
        sysRoleFunctionMapper.insertSelective(sysRoleFunction);
    }

    /**
     * 查询角色具有的功能
     * @param roleId
     * @return
     */
    @Transactional(readOnly = true)
    public List<SysRoleFunctionVO> queryRoleFunctionList(String roleId) {
        return sysRoleFunctionMapper.queryRoleFunctionList(roleId);
    }
}
