/**
 * @file: MyRunnable
 * @author: michael
 * @date: 2020/3/6 10:13
 * @copyright: 南京凯盛
 */
package com.cc.thread;

/**
 *  实现Runnable, 实现步骤：
 *   1. implements Runnable
 *   2. Override run();
 *   3. new Thread(my).start();   //静态代理实现
 *
 * @author michael
 * @version 1.0 Created on 2020/3/6 10:13
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("thread is ----->" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("start=================== " + Thread.currentThread().getName());

        MyRunnable my = new MyRunnable();
        new Thread(my).start();//new thread, 执行顺序由CPU控制

        System.out.println("end=================== " + Thread.currentThread().getName());
    }
}
