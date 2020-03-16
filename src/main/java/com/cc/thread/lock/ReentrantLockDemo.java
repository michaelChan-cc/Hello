/**
 * @file: ReentrantLockDemo
 * @author: michael
 * @date: 2020/3/16 11:17
 * @copyright: 南京凯盛
 */
package com.cc.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  1.ReentrantLock和synchronized都是独占锁 （synchronized加锁解锁的过程是隐式）
 *  2.ReentrantLock和synchronized都是可重入的
 *          synchronized因为可重入因此可以放在被递归执行的方法上,且不用担心线程最后能否正确释放锁；
 *          而ReentrantLock在重入时要却确保重复获取锁的次数必须和重复释放锁的次数一样，否则可能导致其他线程无法获得该锁。
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/16 11:17
 */
public class ReentrantLockDemo {

    //实现公平锁（解决"饥饿”问题），默认false
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            new Thread(()->{ dolock();}, "thread" + i).start();
        }
    }

    /**
     * unlock() 解锁的操作尽量要放在finally代码块中,保证线程正确释放锁
     */
    private static void dolock() {
        //执行两次，比较
        for (int i = 0; i < 2; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁");

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                System.out.println(Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        }
    }

}
