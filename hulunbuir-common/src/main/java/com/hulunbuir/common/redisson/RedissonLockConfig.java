//package com.hulunbuir.common.redisson;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.LocalCachedMapOptions;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
///**
// * <p>
// * explain: 分布式锁配置类
// * <p>
// * 参考与： https://blog.csdn.net/zhangcongyi420/article/details/89980469
// * https://www.cnblogs.com/throwable/p/14264804.html
// * </p>
// *
// * @author wangjunming
// * @since 2020/8/5 16:46
// */
//@Slf4j
//@Configuration
//public class RedissonLockConfig {
//
//    @Value("${spring.redis.host}")
//    private String url;
//
//    @Value("${spring.redis.port}")
//    private String port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    /*@Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        url = String.format("redis://%s:%s", url, port);
//        RedissonClient redisson = null;
//        try {
//            config.useSingleServer()
//                    .setAddress(url).setPassword(password);
//            redisson = Redisson.create(config);
//            log.info("创建成功的分布式锁是：{}", redisson.getConfig().toJSON());
//        } catch (IOException e) {
//            log.error("获取创建成功的分布式锁失败！！", e);
//        }
//        return redisson;
//    }*/
//
//    @Bean(destroyMethod="shutdown")
//    public RedissonClient redissonClient() throws IOException {
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://127.0.0.1:6379").setPassword(password);
//        RedissonClient redissonClient = Redisson.create(config);
//        log.info("创建成功的分布式锁是：{}", redissonClient.getConfig().toJSON());
//        return redissonClient;
//    }
//
////    @Bean
////    CacheManager cacheManager(RedissonClient redissonClient) {
////        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
////        LocalCachedMapOptions options = LocalCachedMapOptions.defaults()
////                .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU)
////                .cacheSize(1000);
////        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
////        config.put("testMap", new LocalCachedCacheConfig(24*60*1000, 12*60*1000, options));
////        return new RedissonSpringLocalCachedCacheManager(redissonClient, config);
//////    }
////@Bean
////CacheManager cacheManager(RedissonClient redissonClient) throws IOException {
////    return new RedissonSpringLocalCachedCacheManager(redissonClient, "classpath:/cache-config.yaml");
////}
//
//}
