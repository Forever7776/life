package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.SysUser;
import com.forever7776.life.core.entity.sys.SysUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 更新用户登录时间
     *
     * @param username
     * @return
     */
    @Update("update sys_user set login_time=now() where username=#{username}")
    int updateLoginTime(@Param("username") String username);

    /**
     * 更新用户登出时间
     *
     * @param username
     * @return
     */
    @Update("update sys_user set logout_time=now() where username=#{username}")
    int updateLogoutTime(@Param("username") String username);
}