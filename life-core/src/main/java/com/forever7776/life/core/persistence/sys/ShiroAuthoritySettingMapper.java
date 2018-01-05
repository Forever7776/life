package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.ShiroAuthoritySetting;
import com.forever7776.life.core.entity.sys.ShiroAuthoritySettingExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShiroAuthoritySettingMapper {
    int countByExample(ShiroAuthoritySettingExample example);

    int deleteByExample(ShiroAuthoritySettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShiroAuthoritySetting record);

    int insertSelective(ShiroAuthoritySetting record);

    List<ShiroAuthoritySetting> selectByExample(ShiroAuthoritySettingExample example);

    ShiroAuthoritySetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShiroAuthoritySetting record, @Param("example") ShiroAuthoritySettingExample example);

    int updateByExample(@Param("record") ShiroAuthoritySetting record, @Param("example") ShiroAuthoritySettingExample example);

    int updateByPrimaryKeySelective(ShiroAuthoritySetting record);

    int updateByPrimaryKey(ShiroAuthoritySetting record);

    /**
     * 查询不需要拦截的url
     *
     * @return
     */
    @Select("SELECT url from shiro_authority_setting")
    List<String> getUrls();
}