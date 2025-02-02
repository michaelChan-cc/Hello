RPC与Dubbo知识点详解

# 一 . RPC的基本概念

## 1.1 RPC协议(Remote Procedure Call Protocol )

```
 远程过程调用协议 ,它是一种通过网络从远程计算机程序上请求服务 , 而不需要底层网络技术的协议 .RPC协议假定某些传输协议的存在，如 TCP 或 UDP，为通信程序之间 携带信息数据。在 OSI 网络通信模型中，RPC 跨越了传输层和应用层。RPC 使得开发包括网络分布式多程序在内的应用程序更加容易。

 RPC 采用客户机/服务器模式。请求程序就是一个客户机，而服务提供程序就是一个服务器。首先，客户机调用进程发送一个有进程参数的调用信息到服务进程，然后等待应答信 息。在服务器端，进程保持睡眠状态直到调用信息到达为止。当一个调用信息到达，服务器 获得进程参数，计算结果，发送答复信息，然后等待下一个调用信息，最后，客户端调用进 程接收答复信息，获得进程结果，然后调用执行继续进行。
```



## 1.2 RPC框架

```
 在单机时代一台电脑运行多个进程，进程之间无法通讯，显然这会浪费很多资源，因此 后来出现 IPC(Inter-process communication：单机中运行的进程之间的相互通信)，这样就能 允许进程之间进行通讯，比如在一台计算机中的 A 进程写了一个吃饭的方法，那在以前如 果在 B 进程中也要有一个吃饭的方法，必须要在 B 进程中进行创建，但有了 RPC 后 B 只需 要调用 A 进程的程序即可完成，再到后来网络时代的出现， 大家电脑都连起来，这时可不 可以调用其他电脑上的进程呢，当然可以，这样 RPC 框架就出现了。严格意义上来讲：Unix的生态系统中 RPC 可以在同一台电脑上不同进程进行，也可以在不同电脑上进行；而在 windows 里面同一台电脑上不同进程间的通讯还可以采用 LPC(本地访问)。综上：RPC 或 LPC 是上层建筑，IPC 是底层基础。

RPC 框架有很多：比如 JAVA RMI、Thrift、Dubbo、grpc 等。
```



## 1.3 RPC与HTTP , TCP , UDP ,Socket的区别

```
 TCP/UDP : 都是传输协议，主要区别是
                     tcp 协议连接需要 3 次握手，断开需要四次挥手， 是通过流来传输的，就是确定连接后，一直发送信息，传完后断开。
                     udp 不需要进行连接， 直接把信息封装成多个报文，直接发送。所以 udp 的速度更快写，但是不保证数据的完整 性。

 Http：超文本传输协议是一种应用层协议，建立在 TCP 协议之上

 Socket：是在应用程序层面上对 TCP/IP 协议的封装和应用。其实是一个调用接口，方便程序员使用 TCP/IP 协议栈而已。程序员通过 socket 来使用 tcp/ip 协议。但是 socket 并不 是一定要使用 tcp/ip 协议，Socket 编程接口在设计的时候，就希望也能适应其他的网络协议。

 RPC : 是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。 所以RPC的实现可以通过不同的协议去实现比如可以使 http、RMI 等。
```



## 1.4 RPC的运行流程

    RPC的运行流程
    
     首先，要解决通讯的问题，主要是通过在客户端和服务器之间建立 TCP 连接，远程过 程调用的所有交换的数据都在这个连接里传输。连接可以是按需连接，调用结束后就断掉， 也可以是长连接，多个远程过程调用共享同一个连接。
    
     第二，要解决寻址的问题，也就是说，A 服务器上的应用怎么告诉底层的 RPC 框架，如 何连接到 B 服务器（如主机或 IP 地址）以及特定的端口，方法的名称名称是什么，这样才 能完成调用。比如基于 Web 服务协议栈的 RPC，就要提供一个 endpoint URI，或者是从 UDDI(一种目录服务，通过该目录服务进行服务注册与搜索)服务上查找。如果是 RMI 调用的 话，还需要一个 RMI Registry 来注册服务的地址。
    
     第三，当 A 服务器上的应用发起远程过程调用时，方法的参数需要通过底层的网络协 议如 TCP 传递到 B 服务器，由于网络协议是基于二进制的，内存中的参数的值要序列化成 二进制的形式，也就是序列化（Serialize）或编组（marshal），通过寻址和传输将序列化的二 进制发送给 B 服务器。
    
     第四，B 服务器收到请求后，需要对参数进行反序列化（序列化的逆操作），恢复为内 存中的表达方式，然后找到对应的方法（寻址的一部分）进行本地调用，然后得到返回值。 第五，返回值还要发送回服务器 A 上的应用，也要经过序列化的方式发送，服务器 A 接 到后，再反序列化，恢复为内存中的表达方式，交给 A 服务器上的应用
    

##     为什么要使用RPC协议

```
论复杂度，RPC 框架肯定是高于简单的 HTTP 接口的。但毋庸置疑，HTTP 接口由于受 限于 HTTP 协议，需要带 HTTP 请求头，导致传输起来效率或者说安全性不如 RPC。

什么要的场景才是用 ? ?

 问题 ? ?-----现在问题是，遇到怎样的瓶颈了才需要或者说更适合用 RPC（比如像阿里这么大的请求 并发量，简单的 HTTP 肯定达不到预期），但问题是大家所在的公司，要有像阿里这么大的 量是比较少的，甚至说 1/1000 的量可能都没有，那我们还需要使用 RPC 吗？

 分析 -------那 RPC 最大的优点，或者说它相比简单的 HTTP 接口，它的优势、更适合它的业务场景 是怎样呢？简单的 HTTP 又哪里不足，哪些场景明显不太适合呢？
     
     http 接口是在接口不多、系统与系统交互较少的情况下，解决信息初期常使用的一种通信手段；优点就是简单、直接、开发方便。利用现成的 http 协议进行传输。但是如果是一个 大型的网站，内部子系统较多、接口非常多的情况下，RPC 框架的好处就显示出来了， 首先 就是长链接，不必每次通信都要像 http 一样去 3 次握手什么的，减少了网络开销(这个问题 在 http2.0 已经被解决不再算是问题了)； 其次就是 RPC 框架一般都有注册中心，有丰富的 监控管理；发布、下线接口、动态扩展等，对调用方来说是无感知、统一化的操作. 第三个 来说就是安全性。最后就是流行的服务化架构、服务化治理，RPC 框架是一个强力的支撑。
```

##  总结：RPC 是一种概念，http 也是 RPC 实现的一种方式，用 http 交互其实就已经属于 RPC 了。

    问题 :　但是我们为什么要应用 RPC 层呢？
     a. 灵活部署
     b. 解耦
    
     RPC : 远程过程调用。RPC 的核心并不在于使用什么协议。RPC 的目的是让你在本地调用 远程的方法，而对你来说这个调用是透明的，你并不知道这个调用的方法是部署哪里。通过 RPC 能解耦服务，这才是使用 RPC 的真正目的。RPC 的原理主要用到了动态代理模式，至 于 http 协议，只是传输协议而已。
    
     RPC 是一个软件结构概念，是构建分布式应用的理论基础。就好比为啥你家可以用到发 电厂发出来的电？是因为电是可以传输的。至于用铜线还是用铁丝还是其他种类的导线，也 就是用 http 还是用其他协议的问题了。这个要看什么场景，对性能要求怎么样。比如在 java 中的最基本的就是 RMI 技术，它是 java 原生的应用层分布式技术。我们可以肯定的是在传 输性能方面，RMI 的性能是优于 HTTP 的。那为啥很少用到这个技术？那是因为用这个有很 多局限性，首先它要保证传输的两端都要要用 java 实现，且两边需要有相同的对象类型和 代理接口，不需要容器，但是加大了编程的难度，在应用内部的各个子系统之间还是会看到 他的身影，比如 EJB 就是基于 rmi 技术的。这就与目前的 bs 架构的软件大相径庭。用 http 必须要服务端位于 http 容器里面，这样减少了网络传输方面的开发，只需要关注业务开发 即可。

# 二 . Dubbo是什么 ?

 Dubbo 是由阿里巴巴开源的一个高性能、基于 Java 开源的远程调用框架。正如在许多 RPC 系统中一样，Dubbo 是基于定义服务的概念，指定可以通过参数和返回类型远程调用 的方法。在服务器端，服务器实现这个接口，并运行一个 Dubbo 服务器来处理客户端调用。 在客户端，客户机有一个存根，它提供与服务器相同的方法。

 Dubbo 提供三个核心功能：基于接口的远程调用、容错和负载均衡，以及服务的自动注 册与发现。Dubbo 框架广泛的在阿里巴巴内部使用，以及京东、当当、去哪儿、考拉等都在 使用。

 简单的说, dubbo就是个服务框架 ,如果没有分布式的需求 ,其实是不需要用的.只有在分布式的时候, 才有dubbo这样的分布式服务框架的需求 .并且本质上是个服务可调的东西 ,说白了就是个远程服务调用的分布式框架(告别Web Service模式中的WSdl ,以服务者消费者的方式在dubbo上注册).

    节点角色关系图

![在这里插入图片描述](D:\JetBrains\IntelliJ_directory\Learning\doc\dubbo\RPC与Dubbo知识点详解.assets\format,png.png)

在这里插入图片描述

节点角色说明 :

  Provider: 暴露服务的服务提供方。

  Consumer: 调用远程服务的服务消费方。

  Registry: 服务注册与发现的注册中心。

  Monitor: 统计服务的调用次调和调用时间的监控中心。

  Container: 服务运行容器。

调用关系说明 :

  0. 服务容器负责启动，加载，运行服务提供者。

  1. 服务提供者在启动时，向注册中心注册自己提供的服务。

  2. 服务消费者在启动时，向注册中心订阅自己所需的服务。

  3. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于

 长连接推送变更数据给消费者。

  4. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进

 行调用，如果调用失败，再选另一台调用。

  5. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送

 一次统计数据到监控中心。