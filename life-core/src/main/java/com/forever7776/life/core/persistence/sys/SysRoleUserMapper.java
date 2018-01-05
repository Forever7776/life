package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.SysRoleUser;
import com.forever7776.life.core.entity.sys.SysRoleUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleUserMapper {
    int countByExample(SysRoleUserExample example);

    int deleteByExample(SysRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    List<SysRoleUser> selectByExample(SysRoleUserExample example);

    SysRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);

    int updateByExample(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);

    /**
     * 根据用户ID,查询角色ID
     *
     * @param userId
     * @return
     */
    @Select("select role_id roleId from sys_role_user where user_id=#{roleId}")
    String getRoleIdByUserId(@Param("roleId") String userId);
}