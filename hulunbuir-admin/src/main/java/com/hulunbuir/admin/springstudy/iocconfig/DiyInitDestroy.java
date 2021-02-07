package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 23:14
 */
public class DiyInitDestroy implements InitializingBean, DisposableBean, BeanPostProcessor {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("通过实现InitializingBean接口来进行初始化参数");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("通过实现DisposableBean接口来进行销毁bean");
    }


}
