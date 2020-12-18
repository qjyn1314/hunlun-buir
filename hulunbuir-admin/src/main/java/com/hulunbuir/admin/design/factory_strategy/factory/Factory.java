package com.hulunbuir.admin.design.factory_strategy.factory;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 13:59
 */
public interface Factory {

    /**
     * 创建不同国家的食物烹饪技术
     * @author wangjunming
     * @since 2020/12/18 14:01
     */
    Food makeFood(String foodLogo);

}
