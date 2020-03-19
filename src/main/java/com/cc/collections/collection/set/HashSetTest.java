/**
 * @file: HashSetTest
 * @author: michael
 * @date: 2020/3/19 14:03
 * @copyright: 南京凯盛
 */
package com.cc.collections.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 *  Set 注重独一无二的性质,该体系集合用于存储无序(存入和取出的顺序不一定相同)元素，值不能重复。
 *  对象的相等性本质是对象 hashCode 值（java 是依据对象的内存地址计算出的此序号）判断的，如果想要让两个不同的对象视为相等的，就必须覆盖 Object 的 hashCode 方法和 equals 方法。
 *
 *  HashSet = Hash表
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 14:03
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet hSet = new HashSet();
        for (int i = 0; i < 5; i++) {
            System.out.println(hSet.hashCode());
            hSet.add(i);
            System.out.println(hSet.hashCode());
        }
        System.out.println(hSet.toString());
        hSet.add(1);/** **/
        System.out.println(hSet.toString());

        System.out.println(hSet.hashCode());
    }
}
