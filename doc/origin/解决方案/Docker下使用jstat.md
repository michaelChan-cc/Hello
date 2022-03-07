# Docker下使用jstat

## Jstat指令：

###     jstat命令命令格式：

    jstat [Options]vmid [interval] [count]
    参数说明：
        Options，选项，我们一般使用 -gcutil 查看gc情况
        vmid，VM的进程号，即当前运行的java进程号
        interval，间隔时间，单位为秒或者毫秒
        count，打印次数，如果缺省则打印无数次
### 常用指令

    1. jstat -gc pid
           可以显示gc的信息，查看gc的次数，及时间。
           其中最后五项，分别是young gc的次数，young gc的时间，full gc的次数，full gc的时间，gc的总时间。
    
     2.jstat -gccapacity pid
           可以显示，VM内存中三代（young,old,perm）对象的使用和占用大小，
           如：PGCMN显示的是最小perm的内存使用量，PGCMX显示的是perm的内存最大使用量，
           PGC是当前新生成的perm内存占用量，PC是但前perm内存占用量。
           其他的可以根据这个类推， OC是old内纯的占用量。
    
    3.jstat -gcutil pid
           统计gc信息统计。
    
    4.jstat -gcnew pid
          年轻代对象的信息。
    
    5.jstat -gcnewcapacity pid
          年轻代对象的信息及其占用量。
    
    6.jstat -gcold pid
         old代对象的信息。
    
    7.stat -gcoldcapacity pid
         old代对象的信息及其占用量。
    
    8.jstat -gcpermcapacity pid
         perm对象的信息及其占用量。
    
    9.jstat -class pid
         显示加载class的数量，及所占空间等信息。
    
    10.jstat -compiler pid
         显示VM实时编译的数量等信息。
    
    11.stat -printcompilation pid
         当前VM执行的信息。
         
    jstat -gccause pid 1 每格1毫秒输出结果
    jstat -gccause pid 3000 每格3秒输出结果

## 常用说明：

![](C:\Users\michael\AppData\Roaming\Typora\typora-user-images\image-20220218110854258.png)

```
Docker容器中使用jstat过程：

1.     列出docker容器：docker ps
2.     标准输入和关联终端：docker exec -it 容器ID  bash
3.     查找出java进程： ps – ef | grep java
4.     统计gc信息统计： jstat –gcutil 466 3000 每三秒打印一次
```

参数含义：

   S0  — Heap上的 Survivorspace 0 区已使用空间的百分比    

   S1  — Heap上的 Survivorspace 1 区已使用空间的百分比    

   E   — Heap上的 Eden space区已使用空间的百分比    

   O   — Heap上的 Old space 区已使用空间的百分比    

   P   — Perm space 区已使用空间的百分比

  M   —元空间 空间使用率

  CCS   —压缩类空间空间使用率 Compressed class space utilization as a percentage

   YGC  — 从应用程序启动到采样时发生 YoungGC 的次数

   YGCT –从应用程序启动到采样时 Young GC 所用的时间(单位秒)    

   FGC  — 从应用程序启动到采样时发生 Full GC的次数

   FGCT –从应用程序启动到采样时 Full GC 所用的时间(单位秒)    

   GCT  — 从应用程序启动到采样时用于垃圾回收的总时间(单位秒)





## 分代垃圾回收详述

![img](https://img-blog.csdn.net/20180515144741751?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1MDk4NTI2/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

如上图所示，为Java堆中的各代分布。

### Young（年轻代）

年轻代分三个区。一个Eden区，两个 Survivor区。大部分对象在Eden区中生成。当Eden区满时，还存活的对象将被复制到Survivor区（两个中的一个），当这个 Survivor区满时，此区的存活对象将被复制到另外一个Survivor区，当这个Survivor去也满了的时候，从第一个Survivor区复制过来的并且此时还存活的对象，将被复制“年老区(Tenured)”。需要注意，Survivor的两个区是对称的，没先后关系，所以同一个区中可能同时存在从Eden复制过来对象，和从前一个Survivor复制过来的对象，而复制到年老区的只有从第一个Survivor去过来的对象。而且，Survivor区总有一个是空的。

### Tenured（年老代）

年老代存放从年轻代存活的对象。一般来说年老代存放的都是生命期较长的对象。

### Perm（持久代）

用 于存放静态文件，如今Java类、方法等。持久代对垃圾回收没有显著影响，但是有些应用可能动态生成或者调用一些class，例如Hibernate等，在这种时候需要设置一个比较大的持久代空间来存放这些运行过程中新增的类。持久代大小通过-XX:MaxPermSize=进行设置。

### GC类型

GC有两种类型：Scavenge GC和Full GC。
Scavenge GC

一般情况下，当新对象生成，并且在Eden申请空间失败时，就好触发ScavengeGC，堆Eden区域进行GC，清除非存活对象，并且把尚且存活的对象移动到Survivor区。然后整理Survivor的两个区。
Full GC

对整个堆进行整理，包括Young、Tenured和Perm。Full GC比Scavenge GC要慢，因此应该尽可能减少FullGC。有如下原因可能导致Full GC：

Tenured被写满

Perm域被写满

System.gc()被显示调用

上一次GC之后Heap的各域分配策略动态变化GC类型
