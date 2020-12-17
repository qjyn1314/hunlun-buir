package com.hulunbuir.admin.design.adapter.obj;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 一只野鸡
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 17:33
 */
@Slf4j
public class WildCock implements Cock {

    /**
     * 鸡的咕咕叫
     */
    @Override
    public void gobble() {
        log.info("野鸡在鼓鼓的叫.");
    }

    /**
     * 飞
     */
    @Override
    public void fly() {
        log.info("野鸡也会飞.");
    }

}
