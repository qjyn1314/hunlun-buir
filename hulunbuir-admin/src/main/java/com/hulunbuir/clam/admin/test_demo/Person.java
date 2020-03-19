package com.hulunbuir.clam.admin.test_demo;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-16 13:54
 */
public class Person {

    {
        System.out.println("父类的第一个代码块!!!");
    }

    static {
        System.out.println("父类的第一个静态方法!!!");
    }

    private int age;
    private String name;
    private static String county = "CN";

    public static String getCounty() {
        return county;
    }

    public static void setCounty(String county) {
        Person.county = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static {
        System.out.println("父类的第二个静态方法!!!");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    void speak() {
        System.out.println("年龄是：" + age);
    }

    public Person(int age) {
        this.age = age;
    }

    public String compar(Person person) {
        return this.age == person.age ? "是" : "否";
    }

    public Person() {
    }


    public void show(){
        System.out.println("调用父类方法!");
    }

    public void show(int age){
        System.out.println("调用父类方法!输出年龄："+age);
    }




}
