/**
 * @file: VolatileDemo
 * @author: michael
 * @date: 2020/3/16 9:56
 * @copyright: 南京凯盛
 */
package com.cc.thread.lock.visivility;

/**
 *  可见性： synchronized = volatile （两者都是直接读取主内存，不使用缓存机制）
 *  原子性： synchronized 可以
 *
 *
 *  volatile 使用条件：  如果写入变量值不依赖变量当前值，那么就可以用 volatile
 *
 *  https://www.cnblogs.com/FraserYu/p/12424765.html
 *
 * @author michael
 * @version 1.0 Created on 2020/3/16 9:56
 */
public class VolatileDemo {

    private static final int max = 1000000;

    /**
     * count++ 程序代码是一行，但是翻译成 CPU 指令确是三行( 不信你用 javap -c 命令试试)
     * volatile 是非阻塞算法（也就是不排他），当遇到三行 CPU 指令自然就不能保证别的线程不插足了，这就是通常所说的，volatile 能保证内存可见性，但是不能保证原子性
     */
    private volatile int count;

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo v = new VolatileDemo();
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


        //无法保证每次结果= 2*max
        System.out.println("count:" +  v.count);
    }

    private void add() {
        int start = 0;
        while (start++ < max) {
            count++;
        }
    }

}
