package com.hulunbuir.admin.mybatis;

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


    public Bankmoney() {
    }


    public Bankmoney(Integer id, String username, Integer money, String dataJson) {
        this.id = id;
        this.username = username;
        this.money = money;
        this.dataJson = dataJson;
    }
}
