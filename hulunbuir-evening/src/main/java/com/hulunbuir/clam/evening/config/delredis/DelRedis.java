package com.hulunbuir.clam.evening.config.delredis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * explain: 根据注解进行删除redis中的数据
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 15:37
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DelRedis {

    String value() default "";

}
