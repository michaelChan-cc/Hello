/**
 * @file: Join
 * @author: michael
 * @date: 2020/3/10 12:32
 * @copyright: 南京凯盛
 */
package com.cc.thread.state;

/**
 *  线程插队
 *   可以指定线程执行顺序
 *
 * @author michael
 * @version 1.0 Created on 2020/3/10 12:32
 */
public class Join implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Join());
        thread1.start();

        Thread thread2 = new Thread(new Join());
        thread2.start();
        thread2.join();
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start...");
        System.out.println(Thread.currentThread().getName() + " end...");
    }
}
