package com.hulunbuir.clam.afternoon;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class HulunBuirAfternoonApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {


    }


    public static void main(String[] args) {

        String s = RandomUtil.randomNumbers(6);
        System.out.println(s);
        int time = 10;
        double sub = NumberUtil.mul(time, 60);
        System.out.println(sub);





    }

}
