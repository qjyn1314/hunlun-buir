package com.hulunbuir.clam.admin.design.observation;

/**
 * <p>
 * Explain:观察者设计模式的测试
 * <p>
 * https://blog.csdn.net/likun557/article/details/106045522
 *
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-19
 */
public class ObservationTest {
    public static void main(String[] args) {

        //一个被观察者
        ObservationAble observationAble = new ObservationAble();

        //多个观察者
        ObservationPerson zhangsan = new ObservationPerson("zhangsan");
        ObservationPerson lisi = new ObservationPerson("lisi");
        ObservationPerson wangwu = new ObservationPerson("wangwu");

        //被观察者添加多个观察者
        observationAble.addObservation(zhangsan);
        observationAble.addObservation(lisi);
        observationAble.addObservation(wangwu);

        //被观察者进行发布消息
        observationAble.notifyObservation("10：30，大会议室开会！！！");

        System.out.println("--------------------------------");

        observationAble.removeObservation(lisi);
        //被观察者进行发布消息
        observationAble.notifyObservation("10：30，大会议室开会！！！");


    }
}
