/**
 * @file: DynamicProxy
 * @author: michael
 * @date: 2020/3/7 16:59
 * @copyright: 南京凯盛
 */
package com.cc.design.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *   动态代理 ， 反射机制动态创建
 *      1. 定义统一接口
 *      2. 代理实现InvocationHandler， 重写invoke
 *      3. 代理人不清楚 代理的具体动作，通过反射，动态去执行动作
 *
 *
 *      “动态”两个字体现在什么地方？
 * 我们可以这样想，如果你的每一套房子你都请一个代理人帮你打理，每当你想再出租一套房子的时候你得再请一个，这样你会请很多的代理人，花费高额的中介成本，这可以看作常说的“静态代理”。
 *
 * @author michael
 * @version 1.0 Created on 2020/3/7 16:59
 */
public class DynamicProxy {

    /**
     * 客户开始租房
     */
    public static void main(String[] args) {
        //    我们要代理的真实对象--房东
        HouseOwner houseOwner = new HouseOwner();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new Agency(houseOwner);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        RentHouse rentHouse = (RentHouse) Proxy.newProxyInstance(handler.getClass().getClassLoader(), houseOwner
                .getClass().getInterfaces(), handler);//一个动态代理类，中介

        System.out.println("当前运行位置：" + rentHouse.getClass().getName());
        rentHouse.rent();
        rentHouse.charge("10000");
    }
}


/**
 *定义一个借口
 **/
interface RentHouse {
    void rent();//房屋出租
    void charge(String str);//出租费用收取
}

/**
 * 房东
 */
class HouseOwner implements RentHouse {
    @Override
    public void rent() {
        System.out.println("房东说: I want to rent my house");
    }

    @Override
    public void charge(String str) {
        System.out.println("房东, You get : " + str + " RMB HouseCharge.");
    }
}
/**
 * 中介
 */
class Agency implements InvocationHandler {

    //　这个就是我们要代理的真实对象，即房东
    private Object subject;

    //  构造方法，给我们要代理的真实对象赋初值
    public Agency(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //　　在代理真实对象前我们可以添加一些自己的操作，中介收取中介费
        System.out.println("before " + method.getName() + " house");
        System.out.println("Method:" + method.getName());

        //        如果方法是 charge 则中介收取100元中介费
        if (method.getName().equals("charge")) {
            method.invoke(subject, args);
            System.out.println("中介说: I will get 100 RMB ProxyCharge.");
        } else {
            //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
            method.invoke(subject, args);
        }

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after " + method.getName() + " house");
        return null;
    }
}
