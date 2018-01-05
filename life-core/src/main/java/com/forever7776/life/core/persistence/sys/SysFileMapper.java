package com.forever7776.life.core.persistence.sys;

import com.forever7776.life.core.entity.sys.SysFile;
import com.forever7776.life.core.entity.sys.SysFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysFileMapper {
    int countByExample(SysFileExample example);

    int deleteByExample(SysFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    int insertSelective(SysFile record);

    List<SysFile> selectByExample(SysFileExample example);

    SysFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByExample(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);
}