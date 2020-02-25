package com.hulunbuir.clam.admin.design.factory.messagefactory;

import org.springframework.context.annotation.Bean;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 10:16
 */
public interface MessageFactoryService {

    String HC = "HC";
    String CL = "CL";
    String ALI = "ALI";

    Message getMessageServiceImplByType();

}
