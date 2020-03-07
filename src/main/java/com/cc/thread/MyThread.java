/**
 * @file: MyThread
 * @author: michael
 * @date: 2020/3/6 10:03
 * @copyright: 南京凯盛
 */
package com.cc.thread;

/**
 *  继承Thread, 实现步骤：
 *   1. extends Thread
 *   2. Override run();
 *   3. start();
 *
 * @author michael
 * @version 1.0 Created on 2020/3/6 10:03
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("thread is ----->" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("start===================" + Thread.currentThread().getName());

        Thread thread = new MyThread();
//        thread.run();//函数调用， 按顺序执行
        thread.start();//new thread, 执行顺序由CPU控制

        System.out.println("end===================" + Thread.currentThread().getName());
    }
}
