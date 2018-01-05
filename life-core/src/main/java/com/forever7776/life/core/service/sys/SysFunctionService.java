package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.SysFunction;
import com.forever7776.life.core.persistence.sys.SysFunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能
 *
 * @author 0
 * @date 2017-12-21
 */
@Service
@Transactional
public class SysFunctionService {

    @Autowired(required = false)
    private SysFunctionMapper sysFunctionMapper;

    public void save(SysFunction sysFunction) {
        sysFunctionMapper.insertSelective(sysFunction);
    }

    /**
     * 根据用户ID查询用户所具有的权限
     *
     * @param userId
     * @return
     */
    @CachePut(value = "sysFunctionGetUrls", key = "#root.targetClass.name+'_'+#userId", unless = "#result eq null")
    @Transactional(readOnly = true)
    public List<String> getUrls(String userId) {
        return sysFunctionMapper.getUrls(userId);
    }
}
