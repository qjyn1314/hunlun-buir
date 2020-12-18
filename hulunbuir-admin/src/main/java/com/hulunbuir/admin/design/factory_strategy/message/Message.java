package com.hulunbuir.admin.design.factory_strategy.message;

/**
 * <p>
 * Explain:抽象的发送短信的公共接口
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:19
 */
public interface Message {

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
     void sendMessage(String otherData,String phone,String signatureCode,String messageTemplateCode,String params);


    /**
     * 封装短信内容
     * @param messageTemplateCode 短信模板唯一标识
     * @param params 短信模板中所需要的参数
     * @author wangjunming
     * @since 2020/5/12 17:45
     */
     String encapsulationMessageContent(String messageTemplateCode,String params);


     /**
      * 获取短信签名
      * @param signatureCode 签名唯一标识
      * @author wangjunming
      * @since 2020/5/12 18:14
      */
     String acquireSignature(String signatureCode);





}
