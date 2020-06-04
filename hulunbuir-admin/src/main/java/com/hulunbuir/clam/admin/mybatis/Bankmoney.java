package com.hulunbuir.clam.admin.mybatis;

import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/13 16:22
 */
@Data
@ToString
public class Bankmoney {

    private Integer id;

    private String username;

    private Integer money;

    private String dataJson;
}
