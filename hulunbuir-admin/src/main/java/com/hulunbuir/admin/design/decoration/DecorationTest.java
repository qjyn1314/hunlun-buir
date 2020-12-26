package com.hulunbuir.admin.design.decoration;

/**
 * <p>
 * explain:装饰者模式，其在java.io中有具体的实践。
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 18:22
 */
public class DecorationTest {

    public static void main(String[] args) {
        // 首先，我们需要一个基础饮料，红茶、绿茶或咖啡
        BaseBeverage beverage = new GreenTea();
        // 开始装饰
        beverage = new Lemon(beverage); // 先加一份柠檬
        beverage = new Mongo(beverage); // 再加一份芒果

        System.out.println(beverage.getDescription() + " 价格：￥" + beverage.cost());
        //"绿茶, 加柠檬, 加芒果 价格：￥16"
    }

}
