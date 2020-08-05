package com.hulunbuir.clam.common.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * <p>
 * explain: 分布式锁配置类，参考与： https://blog.csdn.net/zhangcongyi420/article/details/89980469
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/5 16:46
 */
@Slf4j
@Configuration
public class RedissonLockConfig {

    @Value("${spring.redis.host}")
    private String url;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        url = String.format("redis://%s:%s", url, port);
        config.useSingleServer()
                .setAddress(url).setPassword(password);
        RedissonClient redisson = Redisson.create(config);
        try {
            log.info("创建成功的分布式锁是：{}", redisson.getConfig().toJSON().toString());
        } catch (IOException e) {
            log.error("获取创建成功的分布式锁失败！！", e);
        }
        return redisson;
    }


}
