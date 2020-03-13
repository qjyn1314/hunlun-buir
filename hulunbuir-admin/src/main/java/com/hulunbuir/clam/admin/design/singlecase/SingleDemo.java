package com.hulunbuir.clam.admin.design.singlecase;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Explain:单例模式-GOF-JOF，
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 11:55
 */
public class SingleDemo {

    public static void main(String[] args) {

        HungryManSingle hungryManSingle = HungryManSingle.getInstance();
        String hungryManEnglish = hungryManSingle.getHungryManEnglish();
        System.out.println(hungryManEnglish);

        SlackerSingle instance = SlackerSingle.getInstance();
        String slackerEnglish = instance.getSlackerEnglish();
        System.out.println(slackerEnglish);

    }


}
