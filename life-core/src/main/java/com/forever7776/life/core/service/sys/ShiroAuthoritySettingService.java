package com.forever7776.life.core.service.sys;

import com.forever7776.life.core.entity.sys.ShiroAuthoritySetting;
import com.forever7776.life.core.entity.sys.ShiroAuthoritySettingExample;
import com.forever7776.life.core.persistence.sys.ShiroAuthoritySettingMapper;
import com.forever7776.life.remote.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * shiro配置
 *
 * @author kz
 * @date 2017-12-21
 */
@Service
@Transactional
public class ShiroAuthoritySettingService {

    @Autowired(required = false)
    private ShiroAuthoritySettingMapper shiroAuthoritySettingMapper;

    @Transactional(readOnly = true)
    public List<ShiroAuthoritySetting> queryShiroUrlList() {
        ShiroAuthoritySettingExample example = new ShiroAuthoritySettingExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.NORMAL.getKey());
        return shiroAuthoritySettingMapper.selectByExample(example);
    }


    /**
     * 查询不需要拦截的url
     *
     * @return
     */
    @Cacheable(value = "shiroAuthoritySettingServiceGetUrls", key = "#root.targetClass.name+'_ShiroAuthoritySettingServiceGetUrls'", unless = "#result eq null")
    public List<String> getUrls() {
        return shiroAuthoritySettingMapper.getUrls();
    }

}
