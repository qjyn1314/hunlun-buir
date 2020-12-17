package com.hulunbuir.admin.design.observation;

/**
 * <p>
 * Explain:观察者类---及抽象出来的观察者
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-19
 */
public interface IObservation {

    /**
     * 观察者根据被观察者的操作信息，然后进行处理的接口方法
     * @param message
     */
    void handleMessage(String message);

}
