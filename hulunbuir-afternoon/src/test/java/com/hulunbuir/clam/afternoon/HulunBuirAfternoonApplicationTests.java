package com.hulunbuir.clam.afternoon;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.hulunbuir.clam.common.config.RedisConfig;
import com.hulunbuir.clam.common.mail.MailConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HulunBuirAfternoonApplicationTests {

//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisConfig redisHelper;
    @Test
    void contextLoads() {
        String randomNumbers = RandomUtil.randomNumbers(6);
        redisHelper.setStrKey(MailConstants.VERIFICATION.name(), randomNumbers, 3000);
        Object strValue = redisHelper.getStrValue(MailConstants.VERIFICATION.name());
        System.out.println(strValue);
        Object userValue = redisHelper.getStrValue("user");
        System.out.println(userValue);
    }


    public static void main(String[] args) {

        String s = RandomUtil.randomNumbers(6);
        System.out.println(s);
        int time = 10;
        double sub = NumberUtil.mul(time, 60);
        System.out.println(sub);





    }

}
