package com.hulunbuir.clam.admin.design.factory.messagefactory;

/**
 * <p>
 * Explain:抽象的发送短信的公共类
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:19
 */
public interface Message {

    /**
     * 抽象方法--发送短信
     * 具体的实现在各个继承类中
     *
     * @param tenId:租户ID-用于查找相应的短信签名，以及不同租户所要求的短信模板内容不一致
     * @param contentType:短息模板的类型
     * @param paramsStr:短信模板中所携带的参数，必须以半角英文逗号隔开
     * @author wangjunming
     * @since 2020/2/24 11:28
     */
     void sendMessage(Long tenId, String contentType, String paramsStr);


}
