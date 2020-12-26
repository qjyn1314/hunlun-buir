package com.hulunbuir.admin.design.decoration;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 红茶
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 17:38
 */
@Slf4j
public class BlackTea implements BaseBeverage {


    /**
     * 返回描述
     */
    @Override
    public String getDescription() {
        log.info("红茶");
        return "红茶";
    }

    /**
     * 返回价格
     */
    @Override
    public double cost() {
        log.info("红茶的价格是：{}",15);
        return 15;
    }
}
