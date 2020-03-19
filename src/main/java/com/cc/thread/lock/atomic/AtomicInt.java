/**
 * @file: AtomicInt
 * @author: michael
 * @date: 2020/3/16 14:00
 * @copyright: 南京凯盛
 */
package com.cc.thread.lock.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/16 14:00
 */
public class AtomicInt {

//    private static int a = 1;
        private static AtomicInteger a =  new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
//            a++;
            System.out.println(Thread.currentThread().getName() + ":" + a.addAndGet(-3));
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + ":" + a.addAndGet(5));
        }).start();

//        TimeUnit.SECONDS.sleep(1);
        System.out.println(a);
    }

}
