/**
 * @file: MyCallable
 * @author: michael
 * @date: 2020/3/6 11:02
 * @copyright: 南京凯盛
 */
package com.cc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  实现Callable, 步骤：
 *   1. implements Callable
 *   2. Override run();
 *   3. new Thread(my).start();   //静态代理实现
 *
 * @author michael
 * @version 1.0 Created on 2020/3/6 11:02
 */
public class MyCallable implements Callable<String> {

    private int a;

    public MyCallable(int a) {
        this.a = a;
    }

    @Override
    public String call() throws InterruptedException {

        System.out.println("thread is ----->" + Thread.currentThread().getName());
        System.out.println("param is ----->" + a);
        Thread.sleep(2000);


        return "call 返回值成功";
    }

    public static void main(String[] args) throws Exception {

        System.out.println("start=================== thread ->" + Thread.currentThread().getName());

        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
            Future<String> future = service.submit(new MyCallable(56321));
//        }
        service.shutdown();
        System.out.println(future.get());

        System.out.println("end=================== thread ->" + Thread.currentThread().getName());


    }
}
