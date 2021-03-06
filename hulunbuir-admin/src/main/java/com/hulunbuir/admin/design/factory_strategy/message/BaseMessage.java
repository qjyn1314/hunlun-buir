package com.hulunbuir.admin.design.factory_strategy.message;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * <p>
 * explain: 使用适配器模式，即这个抽象类实现了接口，将一些方法做具体的实现、或不做实现，将真正使用的类继承这个抽象类，将具体用到的方法进行重写，写出来具体的实现。
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
    private static String nestedStr(String smsContent, String smsDate) {
        final Comparator<String> caseInsensitiveOrder = String.CASE_INSENSITIVE_ORDER;
        String dataK = "\\{\\$var}";
        String[] smsContentSplit = smsContent.split(dataK);
        String[] smsDateSplit = smsDate.split(",");
        StringBuilder smsContentData = new StringBuilder();
        for (int i = 0; i < smsContentSplit.length; i++) {
            for (int j = 0; j < smsDateSplit.length; ) {
                try {
                    if (i == smsDateSplit.length) {
                        smsContentData.append(smsContentSplit[i]);
                        break;
                    }
                    smsContentData.append(smsContentSplit[i]).append(smsDateSplit[i]);
                    break;
                } catch (Exception e) {
                    log.error("====>>>发送短信：填充短信模板数据异常!", e);
                }
            }
        }
        return smsContentData.toString();
    }

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
