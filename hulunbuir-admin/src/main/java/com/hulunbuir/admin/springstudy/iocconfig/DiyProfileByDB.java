package com.hulunbuir.admin.springstudy.iocconfig;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/7 23:26
 */
@Data
@Component
public class DiyProfileByDB {

    @Bean("diyDevDb")
    @Profile("dev")
    public DiyDb diyDevDb() {
        return new DiyDb("开发数据库");
    }

    @Bean("diyTestDb")
    @Profile("test")
    public DiyDb diyTestDb() {
        return new DiyDb("测试数据库");
    }

    @Bean("diyDb")
    @Profile("pro")
    public DiyDb diyDb() {
        return new DiyDb("生产数据库");
    }

}
