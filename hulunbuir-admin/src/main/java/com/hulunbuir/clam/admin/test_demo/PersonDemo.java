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
    private int age;

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



    public static void main(String[] args) {
        Person p1 = new Person(12);
        Person p2 = new Person(20);
        String compar = p1.compar(p2);
        System.out.println("是否是同龄人：" + compar);
    }


}
