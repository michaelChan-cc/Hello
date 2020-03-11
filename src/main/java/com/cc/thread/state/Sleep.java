/**
 * @file: Sleep
 * @author: michael
 * @date: 2020/3/10 9:01
 * @copyright: 南京凯盛
 */
package com.cc.thread.state;

/**
 *  线程从 run-> 阻塞状态
 *
 *  1. 模拟网络延迟
 *  2. 倒计时
 *
 * @author michael
 * @version 1.0 Created on 2020/3/10 9:01
 */
public class Sleep implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "网络延迟...");
    }

    public static void main(String[] args) {
       /* for (int i = 0; i < 50; i++) {
            if (i==10){
                new Thread(new Sleep()).run();
            }
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }*/

            new Thread(
                    ()->{
                        System.out.println("开始倒计时：");
                        for (int i = 10; i >= 1; i--) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(i);
                        }
                    }
            ).start();

    }
}
