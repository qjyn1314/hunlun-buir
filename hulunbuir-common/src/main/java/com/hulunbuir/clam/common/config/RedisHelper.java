package com.hulunbuir.clam.common.config;

import com.hulunbuir.clam.parent.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Explain:Redis工具类
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-03 13:31
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate<String, Object> redisStrKeyTemplate;

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
     * @author wangjunming
     * @since 2020/3/3 15:17
     * @param key:
     * @return boolean
     */
    public boolean vaildate(String key){
        Object strValue = getStrValue(key);
        if(null == strValue){
           return true;
        }else{
            return false;
        }
    }




}
