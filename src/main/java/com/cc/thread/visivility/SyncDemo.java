/**
 * @file: VolatileDemo
 * @author: michael
 * @date: 2020/3/16 9:56
 * @copyright: 南京凯盛
 */
package com.cc.thread.visivility;

/**
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/16 9:56
 */
public class SyncDemo {

    private static final  int max = 1000000;

    private int count;

    public static void main(String[] args) throws InterruptedException {
        SyncDemo v = new SyncDemo();
        Thread thread1 = new Thread(()->{
            v.add();
        });
        Thread thread2 = new Thread(()->{
            v.add();
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();


        //保证每次结果= 2*max
        System.out.println("count:" +  v.count);
    }


    /**
     * synchronized 是独占锁/排他锁（就是有你没我的意思），同时只能有一个线程调用 add10KCount 方法，其他调用线程会被阻塞。
     * 所以三行 CPU 指令都是同一个线程执行完之后别的线程才能继续执行，这就是通常说说的 原子性 （线程执行多条指令不被中断）
     */
    private synchronized void add() {
        int start = 0;
        while (start++ < max) {
            count++;
        }
    }

}
