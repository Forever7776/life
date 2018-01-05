package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.SysRoleFunction;
import com.forever7776.life.core.entity.sys.SysRoleFunctionExample;
import com.forever7776.life.remote.common.vo.SysRoleFunctionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleFunctionMapper {
    int countByExample(SysRoleFunctionExample example);

    int deleteByExample(SysRoleFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleFunction record);

    int insertSelective(SysRoleFunction record);

    List<SysRoleFunction> selectByExample(SysRoleFunctionExample example);

    SysRoleFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);

    int updateByExample(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);

    int updateByPrimaryKeySelective(SysRoleFunction record);

    int updateByPrimaryKey(SysRoleFunction record);

    /**
     * 查询角色具有的功能
     *
     * @param roleId
     * @return
     */
    @Select("SELECT r.role_Id roleId,f.id functionId,f.url functionUrl from sys_role_function r,sys_function f where r.functionid=f.id and f.roleid=#{roleId}")
    List<SysRoleFunctionVO> queryRoleFunctionList(@Param("roleId") String roleId);
}