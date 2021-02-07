package com.hulunbuir.admin.springstudy.iocconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 23:50
 */
@Getter
@Setter
@ToString
@Service
public class DiyValue {

    @Value("zhangsan")
    private String diy1name;

    @Value("#{20-6}")
    private Integer age;

    @Value("${diyname}")
    private String diyname;

    private Long Id;

}
