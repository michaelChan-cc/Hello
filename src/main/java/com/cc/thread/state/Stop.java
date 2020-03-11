/**
 * @file: Stop
 * @author: michael
 * @date: 2020/3/10 12:49
 * @copyright: 南京凯盛
 */
package com.cc.thread.state;

/**
 *  destroy() / stop()  @Deprecated 不推荐使用
 *
 *  推荐使用标志位
 *
 * @author michael
 * @version 1.0 Created on 2020/3/10 12:49
 */
public class Stop implements Runnable{
    static boolean isStop = false;


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new Stop());
        thread.start();

        Thread.sleep(10);
        stop();
    }

    @Override
    public void run() {
        int i=0;
        while (true){
            if (isStop){
                System.out.println("stop thread!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " "+ i++);
        }
    }

    static void stop(){
        isStop = true;
    }
}
