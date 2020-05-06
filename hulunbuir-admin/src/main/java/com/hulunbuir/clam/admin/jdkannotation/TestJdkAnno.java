package com.hulunbuir.clam.admin.jdkannotation;

import java.lang.annotation.*;

//定义注解：
@Target(value ={ ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    //注解的参数：参数+参数名（） default "" ；
    String value()  default "";
}

/**
 * <p>
 * explain:学习jdk的注解与反射
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/22 12:41
 */
@Annos("")
public class TestJdkAnno {


    public static void main(String[] args) {


    }


}



