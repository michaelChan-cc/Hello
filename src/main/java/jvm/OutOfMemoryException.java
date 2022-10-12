package jvm;

import java.util.*;

/**
 * java.lang.OutOfMemoryError:
 *
 * GC overhead limit exceeded 循环插入，不断GC, 最后heap放不下
 * Java heap space 对象太大了，超过了heap； 不需要太多GC就不行了。
 */
public class OutOfMemoryException {
    /**
     * 打印日志
     *
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -Xloggc:c-test-gc.log
     * -Xmx10m
     * -XX:+UseParallelGC
     */
    public static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();
        while (true) {
            String val = String.valueOf(r.nextInt());
            dataMap.put(r.nextInt(), val);
            System.out.println(val);
            //GC overhead limit exceeded
        }
    }

    /**
     * 打印到控制台，概要 -XX:+PrintGC -Xms5m -Xmx5m
     *
     */
    public static void addToList() {
        List t = new ArrayList<>();
        int i=0;
        while (true) {
            i++;
            if (i % 10000 == 0) {
                System.out.println(i++);
            }
            //java.lang.OutOfMemoryError: Java heap space
            t.add(new Object());
        }
    }

    public static void main(String[] args) {
//        addRandomDataToMap();
        addToList();
    }
}
