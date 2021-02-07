package com.hulunbuir.admin.ioc.iocconfig;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

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
public class DiyValue {

    @Value("zhangsan")
    private String diy1name;

    @Value("#{20-6}")
    private Integer age;

    @Value("${diyname}")
    private String diyname;

}
