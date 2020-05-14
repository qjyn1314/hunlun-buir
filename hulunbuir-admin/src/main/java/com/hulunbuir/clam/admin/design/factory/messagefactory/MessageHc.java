package com.hulunbuir.clam.admin.design.factory.messagefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Explain:使用辉诚的api进行发送
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:19
 */
@Slf4j
public class MessageHc extends BaseMessage{

    /**
     * 抽象方法--发送短信
     * 具体的实现在各个继承类中
     * @param otherData 某一标识，用于查询某一套的模板，需要根据具体使用场景来判断使用
     * @param phone 短信内容
     * @param signatureCode 签名唯一标识
     * @param messageTemplateCode 模板唯一标识
     * @param params 短信内容
     * @author wangjunming
     * @since 2020/2/24 11:28
     */
    @Override
    public void sendMessage(String otherData,String phone,String signatureCode,String messageTemplateCode,String params) {
        final String messageContent = encapsulationMessageContent(messageTemplateCode, params);
        final String signature = acquireSignature(signatureCode);
        log.info("使用辉城进行发送短信-->手机号：{}，短信唯一标识：{}，短信内容中的参数：{}，短信内容：{}，短信签名唯一标识：{}，短信签名：{}",phone,messageTemplateCode,params,messageContent,signatureCode,signature);


    }

}
