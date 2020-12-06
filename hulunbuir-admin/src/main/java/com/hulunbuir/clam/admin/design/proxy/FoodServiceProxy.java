package com.hulunbuir.clam.admin.design.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Explain:类中的方法首先是自己的实现，然后在方法内去调用了真正的实现类，然后就可以在真正的实现方法前后添加一些拦截或者自己的代码
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 15:14
 */
@Service("foodServiceProxy")
public class FoodServiceProxy implements FoodService {

    @Autowired
    private FoodService foodService = new FoodServiceImpl();

    /**
     * 番茄炒鸡蛋
     *
     * @return void
     * @author wangjunming
     * @since 2020/2/24 15:13
     */
    @Override
    public void scrambledEggWithTomato() {
        System.out.println("开始进行代理，调用真正的实现番茄炒鸡蛋的接口方法");
        foodService.scrambledEggWithTomato();
        System.out.println("开始进行代理，已经翻炒结束，开始上桌了，盛米饭，放菜，香喷喷的番茄炒鸡蛋!!");
    }
}
