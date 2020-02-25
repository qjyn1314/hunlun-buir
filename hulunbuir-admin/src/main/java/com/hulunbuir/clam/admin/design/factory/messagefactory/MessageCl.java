package com.hulunbuir.clam.admin.design.factory.messagefactory;

/**
 * <p>
 * Explain:使用创蓝的api进行发送
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 11:22
 */
public class MessageCl implements Message{
    @Override
    public void sendMessage(Long tenId, String contentType, String paramsStr) {
        System.out.println("使用MessageCl进行发送，tenId="+tenId+"contentType="+contentType+"paramsStr="+paramsStr);
    }
}
