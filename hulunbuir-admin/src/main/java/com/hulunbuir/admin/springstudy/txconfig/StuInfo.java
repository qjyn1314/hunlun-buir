package com.hulunbuir.admin.springstudy.txconfig;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 21:43
 */
@Data
@AllArgsConstructor
public class StuInfo implements Serializable {

    private Integer id;

    private String stuName;

    private String gender;

    private Integer seat;

    private Integer age;

}
