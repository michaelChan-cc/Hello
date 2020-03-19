/**
 * @file: TreeMapDemo
 * @author: michael
 * @date: 2020/3/19 15:37
 * @copyright: 南京凯盛
 */
package com.cc.collections.map;

import java.util.TreeMap;

/**
 *  TreeMap 的实现就是红黑树数据结构，也就说是一棵自平衡的排序二叉树，这样就可以保证当需要快速检索指定节点。
 *
 *  1. 红黑树
 *  2. 有序， 实现SortedMap接口，按照键值升序
 *
 *  使用场景： 排序的映射
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 15:37
 */
public class TreeMapDemo {

    public static void main(String[] args)
    {
        TreeMap<String , Double> map = new TreeMap<>();
        map.put("ccc" , 89.0);
        map.put("aaa" , 80.0);
        map.put("zzz" , 80.0);
        map.put("bbb" , 89.0);
        System.out.println(map);
    }

}
