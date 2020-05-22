/**
 * @file: ThreadLocalTest
 * @author: michael
 * @date: 2020/3/20 17:14
 * @copyright: 南京凯盛
 */
package com.cc.concurrent;

/**
 *  ThreadLocal 线程本地存储，-> 提供线程内局部变量
 *  作用： 在线程生命周期内有效，减少同一线程内函数调用或者组件之间 公共变量的传递复杂
 *
 *  Thread.java -> ThreadLocal.ThreadLocalMap threadLocals = null;
 *
 * @author michael
 * @version 1.0 Created on 2020/3/20 17:14
 */
public class ThreadLocalTest implements Runnable{

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private void setVal(){
        threadLocal.set(99);
//        System.out.println();
    }

    private void getVal(){
        System.out.println(threadLocal.get());
    }

    @Override
    public void run() {
        setVal();
        getVal();
    }

    public static void main(String[] args) {
        new Thread(new ThreadLocalTest()).start();
    }
}
