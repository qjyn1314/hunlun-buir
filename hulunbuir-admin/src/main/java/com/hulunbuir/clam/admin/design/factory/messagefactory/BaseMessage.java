package com.hulunbuir.clam.admin.design.factory.messagefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/12 17:35
 */
@Slf4j
public abstract class BaseMessage implements Message {

    /**
     * 将数据填写到短信模板中--参数位置的是{$var}
     * @param smsContent 带参数的短信模板
     * @param smsDate 该短信模板中所必须填写的数据
     * @author wangjunming
     * @since 2020/5/12 18:14
     */
    private static String nestedStr(String smsContent,String smsDate) {
        String dataK = "\\{\\$var}";
        String[] smsContentSplit = smsContent.split(dataK);
        String[] smsDateSplit = smsDate.split(",");
        StringBuilder smsContentData = new StringBuilder();
        for (int i = 0; i < smsContentSplit.length; i++) {
            for (int j = 0; j < smsDateSplit.length;) {
                try{
                    if(i==smsDateSplit.length) {
                        smsContentData.append(smsContentSplit[i]);
                        break;
                    }
                    smsContentData.append(smsContentSplit[i]).append(smsDateSplit[i]);
                    break;
                }catch (Exception e){
                    log.error("====>>>发送短信：填充短信模板数据异常!",e);
                }
            }
        }
        return smsContentData.toString();
    }

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
    /*@Override
    public void sendMessage(String otherData,String phone,String signatureCode,String messageTemplateCode,String params) {

    }*/

    /**
     * 封装短信内容
     *
     * @param messageTemplateCode 短信模板唯一标识
     * @param params              短信模板中所需要的参数
     * @author wangjunming
     * @since 2020/5/12 17:45
     */
    @Override
    public String encapsulationMessageContent(String messageTemplateCode,String params) {
        final String messageTemplate = MessageTemplateService.template(messageTemplateCode);
        return nestedStr(messageTemplate,params);
    }

    /**
     * 获取短信签名
     *
     * @param signatureCode 签名唯一标识
     * @author wangjunming
     * @since 2020/5/12 18:14
     */
    @Override
    public String acquireSignature(String signatureCode) {
        return MessageSignatureService.signature(signatureCode);
    }
}
