package com.hulunbuir.clam.admin.design.factory.messagefactory;

/**
 * <p>
 * Explain:使用辉诚的api进行发送
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:19
 */
public class MessageHc implements Message{

    @Override
    public void sendMessage(Long tenId, String contentType, String paramsStr) {
        System.out.println("使用MessageHc进行发送，tenId="+tenId+"contentType="+contentType+"paramsStr="+paramsStr);
    }
}
