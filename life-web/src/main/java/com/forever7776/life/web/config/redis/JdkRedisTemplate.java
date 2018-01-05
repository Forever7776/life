package com.forever7776.life.web.config.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis
 * @author kz
 * @date 2017年12月1日14:32:07
 */
public class JdkRedisTemplate extends RedisTemplate<String, Object> {

    public JdkRedisTemplate() {
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        setKeySerializer(new StringRedisSerializer());
        setValueSerializer(serializer);
        setHashKeySerializer(new StringRedisSerializer());
        setHashValueSerializer(serializer);
    }

    @Override
    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        super.setConnectionFactory(connectionFactory);
    }
}
