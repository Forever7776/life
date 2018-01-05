package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.SysFunction;
import com.forever7776.life.core.entity.sys.SysFunctionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysFunctionMapper {
    int countByExample(SysFunctionExample example);

    int deleteByExample(SysFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    List<SysFunction> selectByExample(SysFunctionExample example);

    SysFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysFunction record, @Param("example") SysFunctionExample example);

    int updateByExample(@Param("record") SysFunction record, @Param("example") SysFunctionExample example);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);

    /**
     * 根据用户ID查询用户所具有的权限
     *
     * @param userId
     * @return
     */
    @Select("select url from sys_function sf,sys_role_function srf,sys_role_user sru where sf.id=srf.function_id and srf.role_id=sru.role_id and sru.user_id=#{userId}")
    List<String> getUrls(@Param("userId") String userId);
}