/**
 * @file: Race
 * @author: michael
 * @date: 2020/3/7 13:37
 * @copyright: 南京凯盛
 */
package com.cc.thread.demo;

/**
 *  龟兔赛跑
 *      1.使用同一个RACE跑道
 *      2.分别使用两个不同赛道
 *
 * @author michael
 * @version 1.0 Created on 2020/3/7 13:37
 */
public class Race implements Runnable{

    private static String winner;

    private static int distint = 100;

    @Override
    public void run() {
        for (int step = 1; step <= 105; step++) {
            if (gameover(step)) {
                break;
            }
            //兔子休眠
            if (Thread.currentThread().getName().equals("Rabbit") && step % 50==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "--> 跑了" + step + "步");
        }
    }

    private boolean gameover(int step) {
        if (winner != null) {
            return true;
        }
        if (step == distint) {
            winner = Thread.currentThread().getName();
            System.out.println(" ====================GameOver==================");
            System.out.println("||   Winner --->" + winner +"   ||");
            System.out.println("  ====================GameOver==================");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //使用同一个RACE跑道
        Race thread = new Race();

        //运动员入场
        new Thread(thread, "Rabbit").start();
        new Thread(thread, "Tortoise").start();
    }



}
