package com.hulunbuir.admin.design.observation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * Explain:具体的被观察者角色，
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-20
 */
public class ObservationAble implements IObservationAble{

    /**
     * 用于存储观察者信息的容器
     */
    private List<IObservation> observations;

    /**
     * 构造函数中，初始化容器
     */
    public ObservationAble() {
        observations = new ArrayList<>();
    }

    /**
     * 添加观察者
     *
     * @param observation
     */
    @Override
    public void addObservation(IObservation observation) {
        observations.add(observation);
    }

    /**
     * 删除观察者
     *
     * @param observation
     */
    @Override
    public void removeObservation(IObservation observation) {
        if(!observations.isEmpty()){
            observations.remove(observation);
        }
        /*for(Iterator<IObservation> listIterator = observations.iterator(); listIterator.hasNext();){
            IObservation obs = listIterator.next();
            if(observation.equals(obs)){
                listIterator.remove();
            }
        }*/
    }

    /**
     * 被观察者产生消息，并告诉观察者，消费的是什么信息
     *
     * @param message
     */
    @Override
    public void notifyObservation(String message) {
        for(ListIterator<IObservation> listIterator = observations.listIterator();listIterator.hasNext();){
            IObservation observation = listIterator.next();
            observation.handleMessage(message);
        }
    }

}
