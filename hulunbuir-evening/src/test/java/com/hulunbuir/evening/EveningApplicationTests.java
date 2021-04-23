package com.hulunbuir.evening;

import com.alibaba.fastjson.JSONObject;
import com.hulunbuir.common.config.RedisService;
import com.hulunbuir.common.redisson.RedisLockConstants;
import com.hulunbuir.common.redisson.RedissonLock;
import com.hulunbuir.distributed.evening.AuthProvider;
import com.hulunbuir.distributed.evening.AuthUser;
import com.hulunbuir.evening.persistence.service.ISysPermissionService;
import com.hulunbuir.evening.persistence.vo.SysPermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class EveningApplicationTests {


    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ISysPermissionService service;


    @Test
    void contextLoads() {
        Long userId = 1L;
        final List<?> sysPermissionTrees = service.permissionTree(new SysPermissionTree(Integer.valueOf("0")), userId);
        log.info("查询出来的权限是：{}",sysPermissionTrees);
        String permission = "permission_21";
        String permissionKeys = "permission*";
        Set<String> keys = redisTemplate.keys(permissionKeys);
        log.info("redis中---查询出来的所匹配的key是：{}",keys);
        final Long deletes = redisTemplate.delete(keys);
        log.info("redis中---删除是否成功：{}",deletes>0);
        final Object permissiones = redisService.getStrValue(permission);
        log.info("redis中---删除之后查询出来的权限是：{}",permissiones);
    }

    @Autowired
    private ListOperations<String, Object> listOperations;

    @DubboReference
    private AuthProvider authProvider;

    @Autowired
    private RedissonLock redissonLock;

    @Test
    void contextLoadsList() {
        redissonLock.lock(RedisLockConstants.REDIS_TEST_LOCK);
        try {
            AuthUser authUser = authProvider.queryUser("admin");
            log.info("authUser:{}", JSONObject.toJSONString(authUser));
        } finally {
            redissonLock.unlock(RedisLockConstants.REDIS_TEST_LOCK);
        }
    }




}
