package com.hulunbuir.clam.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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
@Slf4j
@Configuration
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    public static final String PERMISSION = "permission_";
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
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
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
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        try {
            return stringObjectValueOperations.get(key);
        } catch (Exception e) {
            log.error("redis根据-{}-获取值失败，失败原因：{}", key, e);
            return null;
        }
    }

    /**
     * 验证redis中是否存在key
     *
     * @param key:
     * @return boolean
     * @author wangjunming
     * @since 2020/3/3 15:17
     */
    public boolean validationStrValue(String key) {
        Object strValue = getStrValue(key);
        return null == strValue;
    }

    /**
     * 删除redis中的数据
     *
     * @param key:
     * @author wangjunming
     * @since 2020/11/27 22:15
     */
    public boolean deleteByKey(String key) {
        if(StringUtils.isNotBlank(key)){
            try {
                return redisTemplate.delete(key);
            } catch (Exception e) {
                log.error("redis根据key：{}，删除失败，原因是：{}",key,e);
                return Boolean.FALSE;
            }
        }else {
            return Boolean.FALSE;
        }
    }

    /**
     * 删除redis中的数据
     *
     * @param key:一个正则字符串
     * @author wangjunming
     * @since 2020/11/27 22:15
     */
    public boolean deleteByRegularKey(String key) {
        if(StringUtils.isNotBlank(key)){
            key = key+":*";
            try {
                return redisTemplate.delete(key);
            } catch (Exception e) {
                log.error("redis根据key：{}，删除失败，原因是：{}",key,e);
                return Boolean.FALSE;
            }
        }else {
            return Boolean.FALSE;
        }
    }
}
