package com.hulunbuir.admin.design.adapter.clazz;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 17:59
 */
@Slf4j
public class SomeTargetAdapter extends SomeThing implements Target{
    @Override
    public void doSomeThingOne() {
        log.info("SomeTargetAdapter-->doSomeThingOne");
    }

    @Override
    public void doSomeThingTwo() {
        log.info("SomeTargetAdapter-->doSomeThingTwo");
    }

    @Override
    public void doSomeThingThree() {
        log.info("SomeTargetAdapter-->doSomeThingThree");
    }
}
