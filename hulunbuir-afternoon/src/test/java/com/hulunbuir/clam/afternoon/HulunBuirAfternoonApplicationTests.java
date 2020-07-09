package com.hulunbuir.clam.afternoon;

import cn.hutool.core.util.RandomUtil;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserMapper;
import com.hulunbuir.clam.afternoon.generationcode.mapper.CodeGenerationMapper;
import com.hulunbuir.clam.common.config.RedisConfig;
import com.hulunbuir.clam.common.mail.MailConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
@Slf4j
@SpringBootTest
class HulunBuirAfternoonApplicationTests {
    @Autowired
    private RedisConfig redisHelper;
    @Autowired
    private CodeGenerationMapper codeGenerationMapper;
    @Autowired
    private BuirUserMapper buirUserMapper;

    @Test
    void contextLoads() {
        String randomNumbers = RandomUtil.randomNumbers(6);
        redisHelper.setStrKey(MailConstants.VERIFICATION.name(), randomNumbers, 3000);
        Object strValue = redisHelper.getStrValue(MailConstants.VERIFICATION.name());
        System.out.println(strValue);
        Object userValue = redisHelper.getStrValue("user");
        System.out.println(userValue);
    }

    @Test
    void codeGenerationServiceTest() {
        HashMap<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("nickName","wang");
        final BuirUser buirUser = buirUserMapper.selectBuirUser(paramsMap);
        log.info("buirUser：{}",buirUser);
        final List<String> databases = codeGenerationMapper.generationDatabases();
        log.info("查询的数据库有：{}",databases);
    }

}
