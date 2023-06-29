package com.cc.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMap {

    public static void main(String[] args) {

        List<Map<String, String>> target = new ArrayList<>(64);

        /*for (int i = 0; i < 10; i++) {
            Map a = new HashMap();
            a.put("key"+i, String.valueOf(i));
            target.add(a);
        }*/

        Map<String, String> a = new HashMap();
        for (int i = 0; i < 10; i++) {
            a.put("key"+i, String.valueOf(i));
        }
        for (Map.Entry<String, String> entry : a.entrySet()) {
        }

        // 打印整个List
        System.out.println("Complete List:");
        for (Map<String, String> map : target) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println();
        }

//        target.add(new HashMap<>().put("123", "123"));


    }
}
