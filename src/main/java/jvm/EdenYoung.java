package jvm;

public class EdenYoung {

    /**
     * 新生代大小配置参数的优先级：
     * 高：-XX:NewSize/MaxNewSize
     * 中间 -Xmn （NewSize= MaxNewSize）
     * 低：-XX:NewRatio  表示比例，例如=2，表示 新生代：老年代 = 1:2
     *
     * -XX:SurvivorRatio 表示Eden和Survivor的比值，
     * 缺省为8 表示 Eden:FromSurvivor:ToSurvivor= 8:1:1
     *
     * 同样的代码情况下：
     * -Xms20M -Xmx20M -XX:+PrintGCDetails –Xmn2m -XX:SurvivorRatio=2
     * 没有垃圾回收
     * 数组都在老年代
     *
     * -Xms20M -Xmx20M -XX:+PrintGCDetails -Xmn7m -XX:SurvivorRatio=2
     * 发生了垃圾回收
     * 新生代存了部分数组，老年代也保存了部分数组，发生了晋升现象
     *
     * -Xms20M -Xmx20M -XX:+PrintGCDetails -Xmn15m -XX:SurvivorRatio=8
     * 新生代可以放下所有的数组
     * 老年代没放
     *
     * -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:NewRatio=2
     * 发生了垃圾回收
     * 出现了空间分配担保，而且发生了FullGC
     * @param args
     */

    public static void main(String[] args) {
        int cap = 1*1024*1024; //1M

        byte[] b1 = new byte[cap];
        byte[] b2 = new byte[cap];
        byte[] b3 = new byte[cap];
        byte[] b4 = new byte[cap];
        byte[] b5 = new byte[cap];
        byte[] b6 = new byte[cap];
        byte[] b7 = new byte[cap];
        byte[] b8 = new byte[cap];
        byte[] b9 = new byte[cap];
        byte[] b10 = new byte[cap];


        /*byte[] b;
        for (int i = 0; i < 50 ; i++) {
            b = new byte[cap];
        }*/

    }
}
