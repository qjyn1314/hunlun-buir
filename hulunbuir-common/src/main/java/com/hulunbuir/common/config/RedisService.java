package com.hulunbuir.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.Set;
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

    /**
     * 通配符  “*” 号
     */
    public static final String ASTERISK = "*";
    /**
     * 存储权限的key
     */
    public static final String PERMISSION = "permission_";
    /**
     * 删除所有权限的模糊key
     */
    public static final String PERMISSION_DELKEY = PERMISSION + ASTERISK;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    /**
     * 存储数据类型是String的value值
     *
     * @param key:存储的key
     * @param object:key对应的values
     * @param time:过期时间，单位：秒
     * @return void
     * @author wangjunming
     * @since 2020/3/3 13:49
     */
    public void setStrKey(String key, Object object, long time) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, object, time, TimeUnit.SECONDS);
    }

    /**
     * 获取数据类型是String的value值
     *
     * @param key:
     * @return java.lang.Object
     * @author wangjunming
     * @since 2020/3/3 15:04
     */
    public Object getStrValue(String key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        try {
            return valueOperations.get(key);
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
        final Set<String> keys = redisTemplate.keys(key);
        return null != keys && keys.size() > 0;
    }

    /**
     * 根据模糊的key或者根据具体的key，删除redis中的数据
     *
     * @param key:
     * @author wangjunming
     * @since 2020/11/27 22:15
     */
    public boolean deleteByKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            try {
                Set<String> keys = redisTemplate.keys(key);
                log.info("redis中---查询出来的所匹配的key是：{}", keys);
                if (!CollectionUtils.isEmpty(keys)) {
                    return redisTemplate.delete(keys) > 0;
                }
                log.warn("redis中---并没有查询出key：{}，其对应的值", key);
                return Boolean.TRUE;
            } catch (Exception e) {
                log.error("redis根据key：{}，删除失败，原因是：{}", key, e);
                return Boolean.FALSE;
            }
        } else {
            return Boolean.FALSE;
        }
    }

}
