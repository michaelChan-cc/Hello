/**
 * @file: ArrayTest
 * @author: michael
 * @date: 2020/3/7 11:51
 * @copyright: 南京凯盛
 */
package com.cc.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  ArrayList 非线程安全的类
 *    异常：java.util.ConcurrentModificationException
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/7 11:51
 */
public class ArrayTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 保证线程安全
//        List<String> list = new Vector<>();
//        List<String> list = new CopyOnWriteArrayList();
//        List<String> list = Collections.synchronizedList(new ArrayList());
        int n = 10;
        for (int i = 0; i < n; i++) {
            new Thread(
                    () -> {
                        list.add(Thread.currentThread().getName());
                        System.out.println(list);
                    }, "thread_" + i
            ).start();
        }
//        System.out.println("max = " + n + ",  size =" + list);

        /*for (String o : list) {
            System.out.println(o);
        }*/


    }

}
