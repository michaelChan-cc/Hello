/**
 * @file: StaticProxy
 * @author: michael
 * @date: 2020/3/7 16:27
 * @copyright: 南京凯盛
 */
package com.cc.designmode.proxy;

/**
 *  静态代理
 *      1. 有一个接口
 *      2. 被代理人实现接口
 *      3. 代理人实现接口，增加构造函数申明代理对象，（可以额外增加其他动作）
 *      4. new 代理人(代理对象).say(" i love u");
 *
 * @author michael
 * @version 1.0 Created on 2020/3/7 16:27
 */
public class StaticProxy {
    public static void main(String[] args) {
        Person person = new Person();
        new WeddingProxy(person).say(" i love u");
    }
}

interface Merry{
    void say(String date);
}

class Person implements Merry{

    @Override
    public void say(String somethings) {
        System.out.println("出席婚礼, 说:" + somethings);
    }
}

class WeddingProxy implements Merry{

    private Merry merry;

    public WeddingProxy(Merry merry) {
        this.merry = merry;
    }

    @Override
    public void say(String somethings) {
        beforeWeddinig();
        System.out.println("婚庆公司: 欢迎新人发言 -->");
        merry.say(somethings);
        afterWeddinig();
    }

    private void beforeWeddinig(){
        System.out.println("预约婚礼");
    }

    private void afterWeddinig(){
        System.out.println("付费");
    }

}