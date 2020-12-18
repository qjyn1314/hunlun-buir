package com.hulunbuir.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * explain: 创建注解
 * 参考：https://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/15 16:43
 */ //定义注解：
@Target(value ={ ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    //注解的参数：参数+参数名（） default "" ；
    String value()  default "";
}
