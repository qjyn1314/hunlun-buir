package com.hulunbuir.clam.admin.test_demo;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-08 15:11
 */
class Person {
    static {
        System.out.println("B");
    }

    private int age;
    private String name;
    private static String county = "CN";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static {
        System.out.println("D");
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
}

public class PersonDemo {

    static {
        System.out.println("A");
    }
    /**
     子父类中的构造方法：
     其子类的构造函数默认的隐式语句super();
     如果父类中没有空的构造参数
     子类的所有的构造函数，默认都会访问父类中的默认构造函数
     其子类的构造函数第一行默认的隐式语句super();


     */
    public static void main(String[] args) {
        Person p1 = new Person(12);
        Person p2 = new Person(20);
        p2.setName("zhangsan");
        String compar = p1.compar(p2);
        System.out.println("是否是同龄人：" + compar);
    }

    static {
        System.out.println("C");
    }

}

class Fu{
    Fu(int x){
        System.out.println(x);
    }
}

class Zi extends Fu{
    Zi(){
        super(2);
    }
}

