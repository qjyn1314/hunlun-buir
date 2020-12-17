package com.hulunbuir.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:17
 */
@Data
@AllArgsConstructor
public class UsersPo {

    private Long userId;
    private String phone;
    private String username;

}
