package com.hulunbuir.admin.design.adapter.obj;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 鸭接口有 fly() 和 quare() 两个方法，鸡 Cock 如果要冒充鸭，fly() 方法是现成的，但是鸡不会鸭的呱呱叫，没有 quack() 方法。这个时候就需要适配了：
 * 就是鸡来适配鸭，
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 17:37
 */
@Slf4j
public class CockAdapter implements Duck {


    private final Cock cock;

    public CockAdapter(Cock cock) {
        this.cock = cock;
    }

    /**
     * 鸭的呱呱叫
     */
    @Override
    public void quack() {
        log.info("即将两个不同的事物（方法）进行组合，在这里可以获取到鸡的咕咕叫，并对鸡的咕咕叫做操作，比如，使用扩音器对鸡的声音进行扩大然后在放出来");
        this.cock.gobble();
    }

    /**
     * 飞
     */
    @Override
    public void fly() {
        this.cock.fly();
    }
}
