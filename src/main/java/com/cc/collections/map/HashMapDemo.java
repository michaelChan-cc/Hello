/**
 * @file: HashMapDemo
 * @author: michael
 * @date: 2020/3/19 14:22
 * @copyright: 南京凯盛
 */
package com.cc.collections.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  1. hash
 *  2. 数组 + 链表 + 红黑树
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 14:22
 */
public class HashMapDemo {

    //    Map t = new HashMap();
    static Map t = new Hashtable();// extends Dictionary<K,V> ， 写法复制HashMap, 所有方法加锁
//    static Map t = new ConcurrentHashMap();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                t.put(i, i + 2000);
            }
            System.out.println(t);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                t.put(i, i + 7000);
            }
            System.out.println(t);
        }).start();

    }
}
