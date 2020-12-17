package com.hulunbuir.admin.design.factory_strategy;

import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:短信模板
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/12 17:36
 */
@Component
public class MessageTemplateService {

    public static String template(String templateType){
        return "欢迎注册呼伦贝尔大草原，验证码：{$var}";
    };

}
