package com.hulunbuir.admin.design.observation;

/**
 * <p>
 * Explain:被观察者类----即抽象出来的类，有增删改查的功能
 * 当被观察有发出消息的时候，就告诉观察者发出的是什么信息，
 * 一个被观察者可以有很多个观察者
 * <p>
 * 首先需要添加观察者，将观察者存储到被观察者中
 * <p>
 * 观察者模式的特征：
 *
 * </p >
 *
 * @author wangjunming
 * @since 2019 -08-19
 */
public interface IObservationAble {

    /**
     * 添加观察者
     *
     * @param observation the observation
     */
    void addObservation(IObservation observation);

    /**
     * 删除观察者
     *
     * @param observation the observation
     */
    void removeObservation(IObservation observation);

    /**
     * 被观察者产生消息
     *
     * @param message the message
     */
    void notifyObservation(String message);


}
