package com.forever7776.life.core.service;

import com.forever7776.life.core.entity.sys.SysFile;
import com.forever7776.life.core.entity.sys.SysFileExample;
import com.forever7776.life.core.persistence.sys.SysFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件
 *
 * @author kz
 * @date 2017-11-27
 */
@Service
@Transactional
public class SysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;

    public List<SysFile> queryList() {
        SysFileExample example = new SysFileExample();
        return sysFileMapper.selectByExample(example);
    }

    public void save(SysFile file) {
        sysFileMapper.insertSelective(file);
    }
}
