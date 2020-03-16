/**
 * @file: MyThread2
 * @author: michael
 * @date: 2020/3/12 19:06
 * @copyright: 南京凯盛
 */
package com.cc.thread;

/**
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/12 19:06
 */
public class MyThread2 extends MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread2 : thread is ----->" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new MyThread2().start();
    }
}
