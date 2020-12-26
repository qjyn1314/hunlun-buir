package com.hulunbuir.admin.design.decoration;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 绿茶
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 17:39
 */
@Slf4j
public class GreenTea implements BaseBeverage {
    /**
     * 返回描述
     */
    @Override
    public String getDescription() {
        log.info("绿茶");
        return "绿茶";
    }

    /**
     * 返回价格
     */
    @Override
    public double cost() {
        log.info("绿茶的价格是：{}",13);
        return 13;
    }
}
