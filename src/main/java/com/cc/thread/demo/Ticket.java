/**
 * @file: Ticket
 * @author: michael
 * @date: 2020/3/7 13:01
 * @copyright: 南京凯盛
 */
package com.cc.thread.demo;

/**
 *  抢票DEMO， 多个人实现Runnable，对Ticket操作同一个资源对象；
 *
 * @author michael
 * @version 1.0 Created on 2020/3/7 13:01
 */
public class Ticket implements Runnable{

    int ticketMax = 10;

    @Override
    public void run() {
        while (true){
            if (ticketMax<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName() +"-->抢到第" + ticketMax-- + "张票！" );
        }
    }

    public static void main(String[] args) {
        Ticket thread = new Ticket();

        new Thread(thread, "小敏").start();
        new Thread(thread, "张三").start();
        new Thread(thread, "老师").start();
    }


}
