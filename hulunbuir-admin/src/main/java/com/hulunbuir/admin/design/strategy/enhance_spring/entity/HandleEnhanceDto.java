package com.hulunbuir.admin.design.strategy.enhance_spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleEnhanceDto implements Serializable {

    private String name;

    private String code;

    private String userCode;


}
