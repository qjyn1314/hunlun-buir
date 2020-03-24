package com.hulunbuir.clam.admin.test_demo;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-16 13:55
 */
public class Woman extends Person implements Comparable{
    {
        System.out.println("子类的第一个代码块!!!");
    }

    static {
        System.out.println("子类的第一个静态方法!!!");
    }

    private Integer woman;

    public Integer getWoman() {
        return woman;
    }

    public void setWoman(Integer woman) {
        this.woman = woman;
    }

    public Woman() {
    }

    public Woman(int age) {
        super(age);
    }

    @Override
    public void show() {

    }

    @Override
    public void show(int age) {
//        super.show(age);
        System.out.println("输出子类的年龄!!!");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Woman{" +
                "woman=" + woman +
                '}';
    }
}
