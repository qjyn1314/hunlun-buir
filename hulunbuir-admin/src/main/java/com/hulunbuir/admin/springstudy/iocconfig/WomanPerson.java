package com.hulunbuir.admin.springstudy.iocconfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 21:28
 */
public class WomanPerson  {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WomanPerson() {
    }

    public WomanPerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "WomanPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void init() {
        System.out.println("WomanPerson...方法-初始化-" + this.name + "-init");
    }

    public void destroy() {
        System.out.println("WomanPerson...方法-销毁-" + this.name + "-destory");
    }


    @PostConstruct
    public void annoPostConstruct(){
        System.out.println("WomanPerson...注解--初始化-" + this.name + "-init");
    }

    @PreDestroy
    public void annoPreDestroy(){
        System.out.println("WomanPerson...注解--销毁-" + this.name + "-destory");
    }

}
