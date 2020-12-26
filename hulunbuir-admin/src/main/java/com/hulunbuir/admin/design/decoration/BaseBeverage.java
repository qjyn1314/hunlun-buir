package com.hulunbuir.admin.design.decoration;

/**
 * <p>
 * explain: 被装饰者的基类。饮料抽象基类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 17:36
 */
public interface BaseBeverage {

    /**
     * 返回描述
     */
    String getDescription();

    /**
     * 返回价格
     */
    double cost();

}
