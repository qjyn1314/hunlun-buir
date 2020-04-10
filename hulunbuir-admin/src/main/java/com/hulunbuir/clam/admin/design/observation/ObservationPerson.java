package com.hulunbuir.clam.admin.design.observation;

/**
 * <p>
 * Explain:具体实现了观察者的实现类
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-20
 */
public class ObservationPerson implements IObservation {

    private String name;
    private String message;

    public ObservationPerson(String name) {
        this.name = name;
    }

    /**
     * 观察者根据被观察者的操作信息，然后进行处理的接口方法
     *
     * @param message
     */
    @Override
    public void handleMessage(String message) {
        this.message = message;
        reads();
    }

    private void reads() {
        System.out.println("观察者：" + this.name + "处理了，消息：" + message);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
