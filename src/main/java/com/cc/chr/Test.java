package com.cc.chr;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Test {
    public static LinkedHashMap map = new LinkedHashMap(16);

    private static void put1 (){
        map.put("a", "1");
    }

    private static void put2(){
        map.put("b", "2");
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        // thread1
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            put1();
            map.entrySet().forEach(i -> System.out.println(i.toString()));
        });

        Thread.sleep(1000);
        // thread1
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                put2();
                map.entrySet().forEach(i -> System.out.println(i.toString()));
            }
        });
//
//        System.out.println("1: ");
//        put1();
////        put2();
//
//        System.out.println("2: ");
//        map.entrySet().forEach(i -> System.out.println(i.toString()));
    }


   /* public static void main(String[] args) {
        OkHttpHelper.getReq("www.baidu.com", new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {

            }
        });
    }*/
}
