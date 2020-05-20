package com.hulunbuir.clam.admin.design.adapter;

/**
 * <p>
 * Explain:适配器的使用
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 16:22
 */
public class AdapterDemo {

    public static void main(String[] args) {

        AdapterSpecific adapterSpecific = new AdapterSpecific();
        final String adapterSend = adapterSpecific.adapterSend();
        System.out.println(adapterSend);

    }

}