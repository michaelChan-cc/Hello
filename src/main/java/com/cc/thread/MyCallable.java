/**
 * @file: MyCallable
 * @author: michael
 * @date: 2020/3/6 11:02
 * @copyright: 南京凯盛
 */
package com.cc.thread;

import java.util.concurrent.*;

/**
 *  实现Callable, 步骤：
 *   1. implements Callable
 *   2. Override run();
 *   3. new Thread(my).start();   //静态代理实现
 *
 * @author michael
 * @version 1.0 Created on 2020/3/6 11:02
 */
public class MyCallable implements Callable<Object> {

    private int a;

    public MyCallable(int a) {
        this.a = a;
    }

    @Override
    public Object call() throws Exception {

        System.out.println("thread is ----->" + Thread.currentThread().getName());
        System.out.println("param is ----->" + a);
        return "call 返回值成功";
    }

    public static void main(String[] args) throws Exception {

        System.out.println("start=================== " + Thread.currentThread().getName());

        MyCallable myCallable = new MyCallable(3);

        /*FutureTask<Object> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask, "future");
        thread.start();
        System.out.println(futureTask.get());*/


        ExecutorService service = Executors.newCachedThreadPool();
        Future<Object> future = service.submit(myCallable);
        service.shutdown();
        String rst = (String) future.get();
        System.out.println(rst);


        System.out.println("end=================== " + Thread.currentThread().getName());




    }
}
