package com.hulunbuir.admin.design.factory_strategy.message;

import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:短信签名
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/12 17:37
 */
@Component
public class MessageSignatureService {

    public static String signature(String signatureType){
        return "【呼伦贝尔大草原】";
    };


}
