package com.hulunbuir.clam.admin.design.proxy;

import org.springframework.stereotype.Service;

/**
 * <p>
 * Explain:类中的真正的方法，去具体的实现了内容
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 15:14
 */
@Service("foodService")
public class FoodServiceImpl implements FoodService {
    /**
     * 番茄炒鸡蛋
     *
     * @return void
     * @author wangjunming
     * @since 2020/2/24 15:13
     */
    @Override
    public void scrambledEggWithTomato() {
        System.out.println("真正实现了番茄炒鸡蛋-打鸡蛋-切番茄-起锅-红锅-翻炒-出锅!!!");
    }
}
