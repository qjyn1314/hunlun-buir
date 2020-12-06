package com.hulunbuir.clam.admin.design.proxy;

/**
 * <p>
 * 说明：
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-11
 */
public class HelloServiceImpl implements IHello {

    /**
     * 打印hello的接口
     */
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    /**
     * 代理接口函数
     *
     * @param t 进行解析的参数
     * @param s 解析后返回的参数
     * @return
     */
    @Override
    public String function(String t, String s) {
        s = t.trim();
        s = s.replace("ko","koi");
        s = s.replaceAll("\\s*","");
        return s;
    }

}
