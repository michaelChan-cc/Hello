/**
 * @file: MyCallable2
 * @author: michael
 * @date: 2020/3/12 19:12
 * @copyright: 南京凯盛
 */
package com.cc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *  Callable 线程池，采用缓存的策略
 *
 * @author michael
 * @version 1.0 Created on 2020/3/12 19:12
 */
public class MyCallable2 implements Callable<String> {

    private static int poolSize = 5;

    private String param;

    public MyCallable2(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
//        System.out.println(Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " start...");

        //使用线程池方式
        ExecutorService pool =  Executors.newFixedThreadPool(poolSize);

        List<Future> futures = new ArrayList();

        for (int i = 0; i < poolSize; i++) {
            futures.add(pool.submit(new MyCallable2("Thread[" + i  + "]")));
        }

        pool.shutdown();

        System.out.println("Future return : ");
        futures.forEach(o->{
            try {
                System.out.println(o.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
