package com.hulunbuir.clam.admin.design.singlecase;

/**
 * <p>
 * Explain:饿汉式的单例模式，简单理解为使用的这个人很饿，必须拿到就能吃的，就先创建出来然后进行返回参数
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 11:48
 */
public class HungryManSingle {
    private HungryManSingle() {
    }

    private final static HungryManSingle hungryManSingle = new HungryManSingle();

    public static HungryManSingle getInstance() {
        return hungryManSingle;
    }

    /**
     * 获取饿汉的英文单词
     *
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/2/24 11:54
     */
    public String getHungryManEnglish() {
        return "HungryMan";
    }


}
