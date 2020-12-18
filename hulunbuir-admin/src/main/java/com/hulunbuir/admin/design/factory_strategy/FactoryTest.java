package com.hulunbuir.admin.design.factory_strategy;

import com.hulunbuir.admin.design.factory_strategy.factory.Factory;
import com.hulunbuir.admin.design.factory_strategy.factory.Food;
import com.hulunbuir.admin.design.factory_strategy.factory.FoodFactory;
import com.hulunbuir.admin.design.factory_strategy.factory.FoodTypeEnum;

/**
 * <p>
 * explain: 工厂模式一般适用于，在使用不同短信供应商进行发送短信的时候，以及在不同的云存储服务器上传文件时，
 * <p>
 * 可使用不同的工厂，创建不同的短信供应商，或者云存储服务器供应商
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 14:11
 */
public class FactoryTest {

    public static void main(String[] args) {
        Factory factory = new FoodFactory();
        final Food food = factory.makeFood(FoodTypeEnum.CHINA.getCode());
        final String foodFood = food.createFood(FoodTypeEnum.CHINA.getCode());
        System.out.println(foodFood);
    }

}
