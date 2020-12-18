package com.hulunbuir.admin.design.factory_strategy.factory;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 14:04
 */
public class UnitedStatesFood implements Food {
    /**
     * 烹饪食物
     *
     * @param createMethod
     * @author wangjunming
     * @since 2020/12/18 14:03
     */
    @Override
    public String createFood(String createMethod) {
        return createMethod + "-烹饪美国食物！！";
    }
}
