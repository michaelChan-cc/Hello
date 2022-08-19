package com.cc.spring.util;

import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * StopWatch是Spring核心包中的一个工具类，它是一个简单的秒表工具，可以计时指定代码段的运行时间以及汇总这个运行时间，
 * 使用它可以隐藏使用 System.currentTimeMillis() ，提高应用程序代码的可读性并减少计算错误的可能性。
 *
 * 注意事项   StopWatch对象不是设计为线程安全的，并且不使用同步。
 * 使用场景  一般是在开发过程中验证性能，而不是作为生产应用程序的一部分
 */
public class MyStopWatch {
    public static void main(String[] args) throws InterruptedException {
        StopWatch sw = new StopWatch("hello");

        sw.start("C1");
        Thread.sleep(1000L);
        sw.stop();

        sw.start("C2");
        Thread.sleep(1220L);
        sw.stop();

//        System.out.println(sw.prettyPrint());
//        System.out.println(sw.shortSummary());


//        sw.setKeepTaskList(true); //是否构建TaskInfo信息
        Arrays.stream(sw.getTaskInfo())
                .forEach(sw1 ->
                            System.out.println(
                                    sw1.getTaskName()+" "+
                                    sw1.getTimeMillis()+" "+
                                    sw1.getTimeSeconds()
                            )
                );
    }
}
