/**
 * @file: TreeSetDemo
 * @author: michael
 * @date: 2020/3/19 14:17
 * @copyright: 南京凯盛
 */
package com.cc.collections.collection.set;

import java.util.Set;
import java.util.TreeSet;

/**
 *  TreeSet = 二叉树
 *
 * @author michael
 * @version 1.0 Created on 2020/3/19 14:17
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        Set target = new TreeSet();
        for (int i = 0; i < 5; i++) {
            System.out.println(target.hashCode());
            target.add(i);
            System.out.println(target.hashCode());
        }

        System.out.println(target.toString());
    }
}
