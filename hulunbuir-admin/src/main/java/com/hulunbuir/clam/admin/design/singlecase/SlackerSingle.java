package com.hulunbuir.clam.admin.design.singlecase;

import org.springframework.context.annotation.Bean;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 13:25
 */
public class SlackerSingle {

    private SlackerSingle(){};

    private static volatile SlackerSingle slackerSingle = null;

    //直接就在 getInstance() 方法签名上加上 synchronized，这就不多说了，性能太差。
    public static SlackerSingle getInstance() {
        if (slackerSingle == null) {
            // 加锁
            synchronized (SlackerSingle.class) {
                // 这一次判断也是必须的，不然会有并发问题
                if (slackerSingle == null) {
                    slackerSingle = new SlackerSingle();
                }
            }
        }
        return slackerSingle;
    }
    /**
     * 懒汉的英文单词
     * @author wangjunming
     * @since 2020/2/24 13:30
     * @return java.lang.String
     */
    public String getSlackerEnglish() {
        return "Slacker";
    }


}
