/**
 * @file: LinkedHashMapTest
 * @author: michael
 * @date: 2020/3/19 16:59
 * @copyright: 南京凯盛
 */
package com.cc.collections.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  1. extends HashMap<K,V>
 *  2. 记录插入顺序
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 16:59
 */
public class LinkedHashMapTest {
//    static Map<Integer, Integer> t = new HashMap();
    static Map<Integer, Integer> t = new LinkedHashMap(16, 0.75f, true);

    /**
     * HashMap 的变量说明
     *  capacity  当前数组容量，2^n;
     *  loadFactor 负载因子 默认0.75
     *
     * LinkedHashMap  -  accesOrder
     *   The iteration ordering method for this linked hash map: <tt>true</tt>
     *      * for access-order 访问顺序, <tt>false</tt> for insertion-order 插入顺序.
     *
     * @param args
     */

    public static void main(String[] args) {
        t.put(1, 1);
        t.put(3, 3);
        t.put(2, 2);
        t.put(4, 4);

        t.get(3);

        for (Map.Entry<Integer, Integer> o : t.entrySet()) {
            System.out.println(o.getKey() + " : " + o.getValue());
        }


    }
}
