package com.hulunbuir.clam.admin.design.adapter;

import java.util.HashMap;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 16:22
 */
public class AdapterDemo {

    public static void main(String[] args) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        stringObjectHashMap.put("doc", stringBuilder);
    }

}