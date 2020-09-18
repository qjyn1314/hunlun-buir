package com.hulunbuir.clam.admin.design.factory.messagefactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Set;

/**
 * Explain:创建发送短信的工具的工厂
 *
 * @author wangjunming
 * @since 2020-02-24 10:20
 */
@Component("messageFactory")
@ConfigurationProperties(prefix = "buir.message")
public class MessageFactory {
    private HashMap<String, Integer> map;

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    String HC = "HC", ALI = "ALI";

    /**
     * 获取优先级最高的api接口类型
     *
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/2/25 13:42
     */
    private String getMessageType() {
        String messageType = "";
        IntSummaryStatistics stats = map.values().stream().mapToInt((x) -> x).summaryStatistics();
        Integer max = stats.getMax();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> next : entries) {
            Integer value = next.getValue();
            if (value.equals(max)) {
                messageType = next.getKey();
            }
        }
        return messageType;
    }

    /**
     * 使用创建工厂模式，根据不同的条件进行创建不同的对象
     *
     * @author wangjunming
     * @since 2020/5/19 10:20
     */
    @Bean
    public Message getMessageServiceImplByType() {
        if (HC.equals(getMessageType())) {
            return new MessageHc();
        } else if (ALI.equals(getMessageType())) {
            return new MessageAli();
        } else {
            return null;
        }
    }
}
