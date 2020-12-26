package com.hulunbuir.admin.design.decoration;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 芒果调出的茶
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 17:59
 */
@Slf4j
public class Mongo implements BaseCondiment {

    private BaseBeverage beverage;

    public Mongo(BaseBeverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 返回描述
     */
    @Override
    public String getDescription() {
        String description = beverage.getDescription();
        description = description + "芒果";
        log.info("芒果调出的茶是:{}", description);
        return description;
    }

    /**
     * 返回价格
     */
    @Override
    public double cost() {
        double cost = beverage.cost();
        cost = cost + 10;
        log.info("芒果调出的茶价格是:{}", cost);
        return cost;
    }
}
