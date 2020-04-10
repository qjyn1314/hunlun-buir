package com.hulunbuir.clam.admin.design.proxy;

/**
 * <p>
 * 说明：
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-11
 */
public interface IHello {

    /**
     * 打印hello的接口
     */
    void sayHello(String name);

    /**
     * 代理接口函数
     *
     * @param t 进行解析的参数
     * @param s 解析后返回的参数
     * @return
     */
    String function(String t, String s);

}
