package com.forever7776.life.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis存储key-value类型
 *
 * @author kz
 * @date 2018-01-04
 */
@Component
public class StringRedisOperator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final static int EXPIRE_MINUTES = 30;

    /**
     * 设值
     *
     * @param key
     * @param value
     * @param expireMinutes 多少分钟过期
     */
    public void setSurroundingServiceMobile(String key, String value, Integer expireMinutes) {
        if (expireMinutes == null) {
            expireMinutes = EXPIRE_MINUTES;
        }
        stringRedisTemplate.boundValueOps(key).set(value, expireMinutes, TimeUnit.MINUTES);
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String getSurroundingServiceMobile(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

}
