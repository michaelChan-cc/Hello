/**
 * @file: LinkedListTest
 * @author: michael
 * @date: 2020/3/19 10:30
 * @copyright: 南京凯盛
 */
package com.cc.collections.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *  LinkedList 是用链表结构存储数据的，很适合数据的动态插入和删除，随机访问和遍历速度比较
 *  慢。另外，他还提供了 List 接口中没有定义的方法，专门用于操作表头和表尾元素，可以当作堆
 *  栈、队列和双向队列使用。
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 10:30
 */
public class LinkedListTest {
    static LinkedList list = new LinkedList();

    public static void main(String[] args) {
//        add();

        compareOn();

    }

    private static void add(){
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);

        list.addFirst("start");
        System.out.println(list);

        list.addLast("last");
        System.out.println(list);
    }

    private static void compareOn(){
        int count = 1000000;

        List<Integer> list1 = new ArrayList();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list1.add(i);
        }
        System.out.println("ArrayList add*" +count + " :" + (System.currentTimeMillis() - t1));

        List<Integer> list2 = new Vector<>();
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list2.add(i);
        }
        System.out.println("Vector add*" +count + " :" + (System.currentTimeMillis() - t2));

        List<Integer> list3 = new LinkedList<>();
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list3.add(i);
        }
        System.out.println("LinkedList add*" +count + " :" + (System.currentTimeMillis() - t3));

        /*long t11 = System.currentTimeMillis();
        System.out.println("ArrayList ：" + list1);
        long t12 = System.currentTimeMillis();


        long t21 = System.currentTimeMillis();
        System.out.println("Vector ：" + list2);
        long t22 = System.currentTimeMillis();


        long t31 = System.currentTimeMillis();
        System.out.println("LinkedList ：" + list3);
        long t32 = System.currentTimeMillis();

        System.out.println("ArrayList get*" +count + " :" + (t12-t11));
        System.out.println("Vector get*" +count + " :" + (t22-t21));
        System.out.println("LinkedList get*" +count + " :" + (t32-t31));*/

    }
}
