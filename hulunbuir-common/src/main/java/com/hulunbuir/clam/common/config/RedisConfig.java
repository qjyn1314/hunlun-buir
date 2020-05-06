package com.hulunbuir.clam.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Explain: redis配置信息
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-10 21:49
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisTemplate<String, Object> redisStrKeyTemplate;

    @Bean("redisStrKeyTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用 String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的 key也采用 String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用 jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的 value序列化方式采用 jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 存储key为字符串的key的value值
     *
     * @param key:存储的key
     * @param object:key对应的values
     * @param time:过期时间，单位：秒
     * @return void
     * @author wangjunming
     * @since 2020/3/3 13:49
     */
    public void setStrKey(String key, Object object, long time) {
        ValueOperations<String, Object> stringObjectValueOperations = redisStrKeyTemplate.opsForValue();
        stringObjectValueOperations.set(key, object, time, TimeUnit.SECONDS);
    }

    /**
     * 获取字符串的key的value值
     *
     * @param key:
     * @return java.lang.Object
     * @author wangjunming
     * @since 2020/3/3 15:04
     */
    public Object getStrValue(String key) {
        ValueOperations<String, Object> stringObjectValueOperations = redisStrKeyTemplate.opsForValue();
        return stringObjectValueOperations.get(key);
    }

    /**
     * 验证redis中是否存在key
     *
     * @param key:
     * @return boolean
     * @author wangjunming
     * @since 2020/3/3 15:17
     */
    public boolean vaildate(String key) {
        Object strValue = getStrValue(key);
        if (null == strValue) {
            return true;
        } else {
            return false;
        }
    }
}
