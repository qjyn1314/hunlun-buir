package com.hulunbuir.admin.springstudy.txconfig;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/9 20:59
 */
@Data
@Component
public class DbMessageInfo {
    String driverClassName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    String username = "root";
    String password = "123456";

}
