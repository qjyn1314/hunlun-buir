package com.hulunbuir.admin.springstudy.aopconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 0:00
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.hulunbuir.admin.springstudy.aopconfig")
public class AopConfig {

}
