package com.hulunbuir.clam.evening;

import com.hulunbuir.common.config.RedisService;
import com.hulunbuir.clam.evening.persistence.service.ISysPermissionService;
import com.hulunbuir.clam.evening.persistence.vo.SysPermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class HulunBuirEveningApplicationTests {


    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ISysPermissionService service;


    @Test
    void contextLoads() {
        Integer userId = 1;
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
    @Test
    void contextLoadsList() {

//        listOperations.rightPush();





    }




}
