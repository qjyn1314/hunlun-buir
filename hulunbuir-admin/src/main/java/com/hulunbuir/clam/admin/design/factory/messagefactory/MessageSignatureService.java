package com.hulunbuir.clam.admin.design.factory.messagefactory;

import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
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
