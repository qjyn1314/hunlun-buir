package com.hulunbuir.admin.design.factory_strategy.factory;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 14:00
 */
public class FoodFactory implements Factory {

    /**
     * 创建不同国家的食物
     * @author wangjunming
     * @since 2020/12/18 14:01
     */
    @Override
    public Food makeFood(String foodLogo){
        if (FoodTypeEnum.CHINA.getCode().equals(foodLogo)){
            return new ChineseFood();
        }else if(FoodTypeEnum.UNITED.getCode().equals(foodLogo)){
            return new UnitedStatesFood();
        }else {
            return null;
        }
    };

}
