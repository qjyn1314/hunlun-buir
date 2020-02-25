package com.hulunbuir.clam.admin.design.factory.messagefactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 * Explain:创建发送短信的工具的工厂
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:20
 */
@Component("messageFactory")
@ConfigurationProperties(prefix = "message")
public class MessageFactoryServiceImpl implements MessageFactoryService {
    private HashMap<String, Integer> map;

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    /**
     * 获取优先级最高的api接口类型
     *
     * @author wangjunming
     * @since 2020/2/25 13:42
     * @return java.lang.String
     */
    private String getMessageType() {
        String messageType = "";
        IntSummaryStatistics stats = map.values().stream().mapToInt((x) -> x).summaryStatistics();
        Integer max = stats.getMax();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Iterator<Map.Entry<String, Integer>> iterator = entries.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Integer> next = iterator.next();
            Integer value = next.getValue();
            if (value.equals(max)) {
                messageType = next.getKey();
            }
        }
        return messageType;
    }

    @Override
    @Bean
    public Message getMessageServiceImplByType() {
        if (HC.equals(getMessageType())) {
            return new MessageHc();
        } else if (ALI.equals(getMessageType())) {
            return new MessageAli();
        } else if (CL.equals(getMessageType())) {
            return new MessageCl();
        } else {
            return null;
        }
    }
}
