#  Docker基础

课程链接

> https://www.bilibili.com/video/BV1og4y1q7M4?from=search&seid=18106350651153543104



<img src="D:\JetBrains\IntelliJ_directory\Learning\doc\origin\解决方案\docker\Docker基础（狂神说Docker课程笔记）.assets\image-20220303102814814.png" alt="image-20220303102814814" style="zoom:80%;" />

## Docker为什么出现？

- 开发和运维两套环境，而环境配置十分麻烦。
   如在Windows上开发，要发布到Linux上运行。Docker给以上问题提出了解决方案：
   Java --- Jar(环境）---打包项目带上环境（镜像）---Docker仓库（应用商店）---下载镜像---直接运行
- Docker的思想来自于集装箱，核心思想：隔离。
   即将应用打包装箱，每个箱子是互相隔离的，可以将服务器利用到极致。

| 虚拟机                           | Docker                                |
| -------------------------------- | ------------------------------------- |
| linux centos原生镜像（一个电脑） | 隔离镜像（最核心的环境 +jdk +mysql等) |
| 需要开启多个虚拟机               | 运行镜像就可以了                      |
| 几GB                             | 几MB                                  |

官方文档

> https://docs.docker.com/

仓库地址

> https://hub.docker.com/

## Docker能做什么？

Docker 提供了一个基础系统镜像作为运行应用的基础。Docker 并不在乎你的应用程序是什么、做什么，也就是说，只要是 Linux 系统上的应用都可以运行在 Docker 容器中。这是因为 Docker 提供了一组应用打包、传输和部署的方法，以便你能更好地在容器内运行任何应用。
例如：Docker 只需要一条命令便可以运行 MySQL 数据库：

```powershell
[root@localhost ~]# docker run -d -p 3306:3306 tutum/mysql
```

## 传统虚拟机与Docker对比

[![20201209124302](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201209124302.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201209124302.png)

## Docker的优点(更快)

- 不模拟完整的操作系统，系统内核（kernel）非常小，更少的抽象层（GuestOS：如Centos）
- 容器内的应用直接运行在宿主机的内核，容器本身没有自己的内核，也没有虚拟硬件。
- 每个容器相互隔离，内部都有属于自己的文件系统，互不影响。

## Docker实现DevOps（开发、运维）

- 应用更快速的交付和部署
   打包镜像发布测试，一键运行；不再需要写大量帮助文档，安装程序
- 更便捷的升级和扩缩容？
   部署应用就和搭积木一样
- 更简单的系统运维
   开发和测试的环境高度一致
- 更高效的计算资源利用
   内核级别的虚拟化，可以在一个物理机上运行很多的容器实例，服务器性能可以被压榨到极致。

## Docker的基本组成[#](https://www.cnblogs.com/koktlzz/p/14105026.html#docker的基本组成)

[![20201208171750](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208171750.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208171750.png)

- 镜像（image）：镜像是一种轻量级、可执行的独立软件包，用来打包软件运行环境和基于运行环境开发的软件。它包含运行某个软件所需的所有内容，包括代码、运行时、库、环境变量和配置文件。**相当于一个模板，通过这个模板来创建容器服务，可以通过一个镜像创建多个容器**。
- 容器（container）：独立运行一个或一组应用/基本命令有：启动，停止，删除等/可理解为一个简单的linux系统。
- 仓库（repository）：存放镜像的地方（公有/私有）

## Docker运行原理[#](https://www.cnblogs.com/koktlzz/p/14105026.html#docker运行原理)

Docker是一个Client-Server结构的系统，以守护进程运行在主机上。通过Socket从客户端进行访问。

# Docker的常用命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#docker的常用命令)

[![20201212210110](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212210110.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212210110.png)

## 帮助命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#帮助命令)

```bash
docker version
docker --help            # 帮助信息
docker info              # 系统信息，包括镜像和容器的数量
```

帮助文档地址：https://docs.docker.com/engine/reference/

## 镜像命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#镜像命令)

```bash

docker search 镜像名      # 搜索镜像
docker pull 镜像名        # 下载镜像
```

Docker采用联合文件系统，不同镜像的相同文件无需再次下载：
 [![20201212192925](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212192925.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212192925.png)

```bash
docker rmi 镜像名/id      删除镜像
```

## 容器命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#容器命令)

```bash

docker run [options] 镜像名/id [command]  # 建立容器并启动：           
[options]:                  
        --name=容器名                 # 命名容器以区分不同容器
        -d                           # 在后台运行容器（必须有一个前台进程，否则进程会自动关闭）
	    -it                          # 使用交互方式运行，进入容器查看内容
	    -p 主机端口:容器端口            # 暴露指定容器端口
	    -P                           # 暴露容器所有端口
[command]:
            /bin/bash                    # 控制台
```

[![2](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/2.jpg)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/2.jpg)

```bash

Exit                         # 从容器中退回主机 
CTRL+Q+P                     # 容器不停止退出
docker ps                    # 显示当前运行的容器 
          -a                 # 带出历史运行过的容器
docker rm 容器名/id           # 删除指定容器
docker rm &(docker ps -aq)   # 删除全部容器
```

## 其他命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#其他命令)

```bash

docker start/restart/stop/kill 容器名/id               
docker logs -tf --tail 显示的日志条数 容器名/id  # 查看日志
docker top 容器名/id                 # 查看容器中的进程信息
docker inspect 容器名/id             # 查看镜像的元数据
docker exec -it 容器名/id /bin/bash  # 通常容器以后台方式运行，需要进入其中修改配置：进入容器后开启一个新终端         
docker attach 容器名/id              # 进入容器正在执行的终端
docker cp 容器名/id:容器内路径 主机文件路径       # 从容器内拷贝文件到主机上
```

# Docker镜像详解[#](https://www.cnblogs.com/koktlzz/p/14105026.html#docker镜像详解)

[![133](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208182908.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208182908.png)

## UnionFS（联合文件系统）[#](https://www.cnblogs.com/koktlzz/p/14105026.html#unionfs联合文件系统)

- 联合文件系统（UnionFS）是一种分层、轻量级并且高性能的文件系统，它支持对文件系统的修改作为一次提交来一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下。联合文件系统是 Docker 镜像的基础。镜像可以通过分层来进行继承，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。
- 特性：一次同时加载多个文件系统，但从外面看起来只能看到一个文件系统。联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录。

## 镜像加载原理[#](https://www.cnblogs.com/koktlzz/p/14105026.html#镜像加载原理)

Docker的镜像实际由一层一层的文件系统组成：

- bootfs（boot file  system）主要包含bootloader和kernel。bootloader主要是引导加载kernel，完成后整个内核就都在内存中了。**此时内存的使用权已由bootfs转交给内核**，系统卸载bootfs。可以被不同的Linux发行版公用。
- rootfs（root file  system），包含典型Linux系统中的/dev，/proc，/bin，/etc等标准目录和文件。rootfs就是各种不同操作系统发行版（Ubuntu，Centos等）。因为底层直接用Host的kernel，rootfs只包含最基本的命令，工具和程序就可以了。
- 分层理解
   所有的Docker镜像都起始于一个基础镜像层，当进行修改或增加新的内容时，就会在当前镜像层之上，创建新的容器层。
   容器在启动时会在镜像最外层上建立一层可读写的容器层（R/W），而镜像层是只读的（R/O）。
   [![20201208183559](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208183559.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208183559.png)

```bash

docker commit -m="描述信息" -a="作者" 容器id 目标镜像名:[tag]  # 编辑容器后提交容器成为一个新镜像
```

# 容器数据卷[#](https://www.cnblogs.com/koktlzz/p/14105026.html#容器数据卷)

[![20201208183759](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208183759.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208183759.png)

## 什么是容器数据卷？[#](https://www.cnblogs.com/koktlzz/p/14105026.html#什么是容器数据卷)

为了实现数据持久化，使容器之间可以共享数据。可以将容器内的目录，挂载到宿主机上或其他容器内，实现同步和共享的操作。即使将容器删除，挂载到本地的数据卷也不会丢失。

## 使用容器数据卷[#](https://www.cnblogs.com/koktlzz/p/14105026.html#使用容器数据卷)

使用命令：

```bash

dokcer run -it -v 主机内目录:容器内目录 镜像名/id
```

将容器内目录挂载到主机内目录上，通过**docker inspect**命令查看该容器即可以看到挂载信息：
 [![20201211162429](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211162429.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211162429.png)
 建立挂载关系后，只要使用命令在主机内新建一个文件：

```bash

touch /home/mountdir/test.txt
```

就会在容器内的挂载目录下发现相同的文件（test.txt），从而实现了容器和主机的文件同步和共享：
 [![20201211162933](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211162933.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211162933.png)

## 匿名挂载[#](https://www.cnblogs.com/koktlzz/p/14105026.html#匿名挂载)

```bash

docker run -d  -v 容器内目录  镜像名/id  # 匿名挂载
```

匿名挂载后，使用**docker volume ls**命令查看所有挂载的卷：
 [![20201208184201](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208184201.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208184201.png)
 每一个VOLUME NAME对应一个挂载的卷，由于挂载时未指定主机目录，因此无法直接找到目录。

## 具名挂载[#](https://www.cnblogs.com/koktlzz/p/14105026.html#具名挂载)

```bash

docker run -d  -v 卷名：容器内目录  镜像名/id  # 具名挂载
```

[![20201211164244](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211164244.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211164244.png)
 可以发现挂载的卷：volume01，并通过**docker volume inspect 卷名** 命令找到主机内目录：
 [![20201211164505](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211164505.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211164505.png)

所有docker容器内的卷，在未指定主机内目录时，都在：*/var/lib/docker/volumes/卷名/_data* 下，可通过具名挂载可以方便的找到卷，因此广泛使用这种方式进行挂载。

## 数据卷容器[#](https://www.cnblogs.com/koktlzz/p/14105026.html#数据卷容器)

[![20201208184546](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208184546.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208184546.png)

```bash
docker run -it --name container02 --volumes from container01 镜像名/id  # 将两个容器进行挂载
```

# DockerFile[#](https://www.cnblogs.com/koktlzz/p/14105026.html#dockerfile)

Dockerfile是用来构建docker镜像的脚本文件，实际上就是一个命令脚本。通过这个脚本就可以生成镜像，镜像是一层一层的，那么脚本中的每个命令都是一层。

Dcoker镜像的分层

![image-20220303121042058](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\解决方案\docker\Docker基础（狂神说Docker课程笔记）.assets\image-20220303121042058.png)

Dockerfile是面向开发者的，发布项目时需要做成镜像，那么就需要编写 Dockerfile 脚本文件。涉及如下几个关键点：

    Dockerfile：构建文件，定义了一切的构建步骤，源代码。—— 开发
    DockerImages：通过 Dockerfile 构建生成的镜像，最终发布和运行的产品。—— 部署
    Docker容器：就是运行起来的镜像，对外提供服务。—— 运维
## 构建步骤：[#](https://www.cnblogs.com/koktlzz/p/14105026.html#构建步骤)



```bash
编写一个dockerfile文件,随后运行命令：
docker build -f 文件路径 -t 标签 .  # 文件名为Dockerfile时可省略且最后的.不要忽略
docker run     # 运行镜像
docker push    # 发布镜像
```

## dockerfile命令[#](https://www.cnblogs.com/koktlzz/p/14105026.html#dockerfile命令)

| 命令           | 效果                                                         |
| -------------- | ------------------------------------------------------------ |
| FROM           | 基础镜像：Centos/Ubuntu                                      |
| MAINTAINER     | 镜像作者+邮箱                                                |
| RUN            | 镜像构建的时候需要运行的命令                                 |
| ADD            | 为镜像添加内容（压缩包）                                     |
| WORKDIR        | 镜像工作目录（进入容器时的目录）                             |
| VOLUME         | 挂载的目录                                                   |
| EXPOSE         | 暴露端口配置                                                 |
| CMD/ENTRYPOINT | 指定这个容器启动时要运行的命令（CMD替代先前命令，ENTRYPOINT在先前命令后追加） |
| COPY           | 类似于ADD，将文件拷贝到镜像中                                |
| ENV            | 构建时设置环境变量                                           |

## 构建过程[#](https://www.cnblogs.com/koktlzz/p/14105026.html#构建过程)

- 每个保留关键字（指令）都必须是大写字母
- 从上到下顺序执行
- "#" 表示注释
- 每一个指令都会创建提交一个新的镜像层并提交

## 构建实例[#](https://www.cnblogs.com/koktlzz/p/14105026.html#构建实例)

[![20201208185616](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208185616.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201208185616.png)



## Dockerfile小结

![image-20220303121104718](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\解决方案\docker\Docker基础（狂神说Docker课程笔记）.assets\image-20220303121104718.png)

# Docker网络[#](https://www.cnblogs.com/koktlzz/p/14105026.html#docker网络)

## Docker的网络模式

安装 Docker 以后，宿主机会自动配置一个虚拟的网桥叫 docker0，并且 Docker 会在私有 IP 网段中，选择一个和宿主机不同的IP地址和子网分配给 docker0，例如将 172.17.0.1/16 分配给 docker0 网桥 。
一般情况下，使用 Docker 创建一个容器的时候，都会自动创建一对虚拟的网络接口（叫做veth-pair），分别放在宿主机和新容器中，这就是连接各种虚拟网络设备的桥梁。宿主机一端的虚拟接口（即veth）会连接到 docker0 网桥上；而容器一端的虚拟接口（即eth0）只能在该容器内可见，并且会从网桥可用地址段中获取一个空闲地址分配给该容器（例如172.17.0.2/16）。通过这种方式，宿主机可以跟容器通信，容器之间也可以相互通信。Docker 就创建了在宿主机和所有容器之间一个虚拟共享网络。当然用户也可以通过 docker network 命令来手动管理网络。

![在这里插入图片描述](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\解决方案\docker\Docker基础（狂神说Docker课程笔记）.assets\watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAd3hmYm9sZw==,size_20,color_FFFFFF,t_70,g_se,x_16.png)

安装Docker的时候，它会自动创建四个网络：bridge（创建容器默认连接到此网络），host，container，none。在Docker 1.9版本以后新增了用户自定义网络模式

```
bridge模式（即 --net=bridge），这是Dokcer网络的默认设置，为容器创建独立的网络命名空间，容器具有独立的网卡等所有单独的网络栈，是最常用的使用方式。 在 docker run 启动容器的时候，如果不加 --net 参数，就默认采用这种网络模式。安装完 Docker，系统会自动添加一个供 Docker 使用的网桥 docker0，我们创建一个新的容器时， 容器通过 DHCP 获取一个与 docker0 同网段的IP地址，并默认连接到 docker0 网桥，以此实现容器与宿主机的网络互通。

host模式（即 --net=host）， 这个模式下创建出来的容器，直接使用容器宿主机的网络命名空间。 将不拥有自己独立的 Network Namespace，即没有独立的网络环境。它使用宿主机的ip和端口。

container模式（即 --net=container:name OR id）， 与host模式类似，只是容器将与指定的容器共享网络命名空间。 这个模式就是指定一个已有的容器，共享该容器的IP和端口。除了网络方面两个容器共享，其他的如文件系统，进程等还是隔离开的。

none模式（即 --net=none）， 为容器创建独立网络命名空间，但不为它做任何网络配置，容器中只有lo，用户可以在此基础上，对容器网络做任意定制。 这个模式下，Dokcer不为容器进行任何网络配置。需要我们自己为容器添加网卡，配置IP。

自定义模式：允许容器使用第三方的网络实现或者创建单独的 bridge 网络，提供网络隔离能力。
```


## 理解Doker0[#](https://www.cnblogs.com/koktlzz/p/14105026.html#理解doker0)

通过命令**ip addr**查看本地ip地址，我们发现除了本机回环地址和埃里远的内网地址外，还多了一个网卡：Docker0，这是Docker服务启动后自动生成的。
 [![20201211165741](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211165741.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211165741.png)
 而如果进入一个正在后台运行的tomcat容器，同样使用**ip addr**命令，发现容器得到了一个新的网络：**12: eth@if13**，ip地址：**172.17.0.2**。这是Docker在容器启动时为其分配的。
 [![1607676753(1)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/1607676753(1).png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/1607676753(1).png)
 思考一个问题：此时我们的linux主机可以ping通容器内部（**172.17.0.2**）吗？（**注意与容器暴露端口相区分**）
 [![20201211170424](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211170424.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211170424.png)

- linux可以ping通docker容器内部，因为docker0的ip地址为**172.17.0.1**，容器为**172.17.0.2**。
- 原理：我们每启动一个docker容器，docker就会给容器分配一个默认的可用ip，我们只要安装了docker，就会有一个网卡docker0(bridge)。网卡采用桥接模式，并使用veth-pair技术（veth-pair就是一堆虚拟设备接口，成对出现，一段连着协议，一段彼此相连，充当一个桥梁。）。
- 这时我们退出容器，回到主机再次观察主机的ip地址：
   [![20201211170810](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211170810.png)](https://cdn.jsdelivr.net/gh/koktlzz/NoteImg@main/20201211170810.png)
   我们惊奇地发现了一个新网络**13: vethda1df4b@if12**，对应容器内网络地址的**12: eth@if13**。
- 容器和容器之间是可以互相ping通的：容器1→Docker0→容器2
   [![20210131140833](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20210131140833.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20210131140833.png)
   docker中的所有网络接口都是虚拟的 ，转发效率高。删除容器后，对应的网桥也随之删除。

## --link[#](https://www.cnblogs.com/koktlzz/p/14105026.html#--link)

若编写一个微服务并连接数据库，如果数据库ip改变，如何根据容器名而不是ip访问容器？显然，直接使用容器名是无法ping通容器内部的：
 [![20201212200850](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212200850.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212200850.png)
 这时我们可以在容器启动命令中加入一个选项：**--link**，使得我们可以根据容器名来访问容器。

```bash

docker run -d -P --link 容器名/id 镜像名/id
```

[![20201212200748](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212200748.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212200748.png)
 然而反向就不可以ping通，这是因为--link的本质是把需要连接的容器名/id写入启动容器的配置文件中，即增加了一个ip和容器名/id的映射：
 [![20201212201930](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212201930.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212201930.png)
 目前已经不建议使用这种方式。

## 自定义网络[#](https://www.cnblogs.com/koktlzz/p/14105026.html#自定义网络)

我们使用命令：

```bash

docker network ls    # 查看所有的docker网络
```

[![20201212202126](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212202126.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212202126.png)
 docker中的网络模式有：

- bridge：桥接（docker默认）/
- none：不配置网络 /
- host：和宿主机共享网络

**docker run** 命令默认带有一个参数--net bridge，此处的bridge指的就是docker0。如果我们不想使用docker0，那如何创建一个新的网络呢？

```bash

docker  network create --driver 网络模式 --subnet 子网ip --gateway 网关 网络名         
```

[![20201212205640](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212205640.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212205640.png)
 我们不仅在**docker network ls**命令下发现了这个新创建的网络newnet，还可以使用**docker network inspect**命令查看其详细信息，包括了我们创建时定义的子网ip和网关：
 [![20201212202733](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212202733.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212202733.png)
 只要两个容器启动时都通过 **--net**，选用了同一个已创建的网络，不同容器间即可通过ip地址或容器名/id连通:
 [![20201212203023](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212203023.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212203023.png)

## 网络连通[#](https://www.cnblogs.com/koktlzz/p/14105026.html#网络连通)

[![20201212204434](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204434.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204434.png)
 对于建立在不同网络下(docker0, newnet)的两个容器tomcat01和tomcat02，他们的网段不同，因此是无法彼此ping通容器内部的：
 [![20201212204311](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204311.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204311.png)
 这时我们需要通过**docker network connect**命令打通容器与网络之间的连接：

```bash

docker network connect 网络名 容器名/id
```

[![20201212203803](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212203803.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212203803.png)
 这个功能类似于将一个容器赋予多个ip地址，同样可以用**docker network inspect**命令查看网络连通后，该网络的变化：
 [![20201212204235](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204235.png)](https://cdn.jsdelivr.net/gh/koktlzz/ImgBed@master/20201212204235.png)
 原本newnet网络中只含有tomcat02，现在增加了tomcat01，因此可以连通。

    操作别人，首先需要使用 docker network connect 来实现网络连通！

# Docker Compose

## Docker Compose是什么？

Docker Compose 是对 Docker 容器进行编排的工具，定义和运行多容器的应用，可以一条命令启动多个容器。
使用三部曲：

    Dockerfile 定义应用的运行环境
    docker-compose.yml 定义组成应用的各服务，服务编排
    docker-compose up 启动整个应用

## 安装 Docker Compose

```
#下载 docker-compose，你可以通过修改URL中的版本，可以自定义您的需要的版本

[root@localhost ~]# curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose 

修改可执行权限

[root@localhost ~]# chmod +x /usr/local/bin/docker-compose

查看版本，安装成功

[root@localhost bin]# docker-compose -version 
docker-compose version 1.25.5, build 8a1c60f6
```

# 可视化

- portainer



# Docker Compose 常用命令

```shell
#默认使用docker-compose.yml构建镜像
[root@localhost ~]# docker-compose build
[root@localhost ~]# docker-compose build --no-cache

#指定不同yml文件模板用于构建镜像
[root@localhost ~]# docker-compose build -f docker-compose-1.yml

#列出Compose文件构建的镜像
[root@localhost ~]# docker-compose images                          

#启动所有编排容器服务,该命令十分强大，它将尝试自动完成包括构建镜像，（重新）创建服务，启动服务，并关联服务相关容器的一系列操作。
[root@localhost ~]# docker-compose up -d

#查看正在运行中的容器
[root@localhost ~]# docker-compose ps 

#查看所有编排容器，包括已停止的容器
[root@localhost ~]# docker-compose ps -a

#停止 up 命令所启动的容器，并移除网络
[root@localhost ~]# docker-compose down

# 进入指定容器执行命令
[root@localhost ~]# docker-compose exec nginx bash 
[root@localhost ~]# docker-compose exec web python manage.py migrate --noinput

# 查看web容器的实时日志
[root@localhost ~]# docker-compose logs -f web

# 启动已经存在的服务容器
[root@localhost ~]# docker-compose start

# 停止已经处于运行状态的容器，但不删除它，可以再次重启
[root@localhost ~]# docker-compose stop

# 重新启动停止服务的容器
[root@localhost ~]# docker-compose restart web

# 暂停web容器
[root@localhost ~]# docker-compose pause web

# 恢复web容器
[root@localhost ~]# docker-compose unpause web

# 删除web容器，删除前必需停止stop web容器服务
[root@localhost ~]# docker-compose rm web  

# 查看各个服务容器内运行的进程 
[root@localhost ~]# docker-compose top  
```



## Docker Compose 配置编写规则

```shell
# 版本号
version: "3"
# 服务
services:
 # 服务名称
  redis:
   # 镜像名: 仓库/标签:版本
    image: redis:alpine
   # 暴露端口信息
    ports:
      - "6379"
   # 添加环境变量.可以使用数组或字典两种形式
   # 任何布尔值:true, false, yes, no 需要用引号括起来,以确保它们不被YAML解析器转换为True或False 
    environment:
  db:
    image: postgres:9.4

    # 定义全局挂载卷
​    volumes:
   - db-data:/var/lib/postgresql/data
     tworks:
        - backend
          ploy:
                placement:
          constraints: [node.role == manager]

```

## 使用 Docker Compose 一键部署WordPress博客

```shell
# 创建一个新的项目目录

[root@localhost home]# mkdir wp_compose

# 进入到项目目录
[root@localhost home]# cd wp_compose

# 创建 docker-compose.yml 文件，编排你的服务，主要内容包括WordPress博客和一个单独的MySQL实例卷挂载数据持久化
[root@localhost wp_compose]# vim docker-compose.yml
version: "3.3"
services:
  db:
    image: mysql:5.7
    volumes:

   - db_data:/var/lib/mysql
     start: always
         environment:
           MYSQL_ROOT_PASSWORD: somewordpress
           MYSQL_DATABASE: wordpress
           MYSQL_USER: wordpress
           MYSQL_PASSWORD: wordpress
       wordpress:
         depends_on:
        - db
          age: wordpress:latest
              volumes:
             - wordpress_data:/var/www/html
               rts:
                  - "8000:80"
                    start: always
                        environment:
                          WORDPRESS_DB_HOST: db
                          WORDPRESS_DB_USER: wordpress
                          WORDPRESS_DB_PASSWORD: wordpress
                          WORDPRESS_DB_NAME: wordpress
                    volumes:
                      db_data: {}
                      wordpress_data: {}

# 启动博客项目
[root@localhost home]# docker-compose up -d

# 预览效果
http://your_ip:8000/
```

# 编排工具

```
Docker 这个东西所扮演的角色，容易理解，它是一个容器引擎，也就是说实际上我们的容器最终是由Docker创建，运行在Docker中，其他相关的容器技术都是以Docker为基础，它是我们使用其他容器技术的核心。

Docker-Compose 是用来管理你的容器的，有点像一个容器的管家，想象一下当你的Docker中有成百上千的容器需要启动，如果一个一个的启动那得多费时间。有了Docker-Compose你只需要编写一个文件，在这个文件里面声明好要启动的容器，配置一些参数，执行一下这个文件，Docker就会按照你声明的配置去把所有的容器启动起来，只需docker-compose up即可启动所有的容器，但是Docker-Compose只能管理当前主机上的Docker，也就是说不能去启动其他主机上的Docker容器

Docker Swarm 是一款用来管理多主机上的Docker容器的工具，可以负责帮你启动容器，监控容器状态，如果容器的状态不正常它会帮你重新帮你启动一个新的容器，来提供服务，同时也提供服务之间的负载均衡，而这些东西Docker-Compose 是做不到的

Kubernetes它本身的角色定位是和Docker Swarm 是一样的，也就是说他们负责的工作在容器领域来说是相同的部分，都是一个跨主机的容器管理平台，当然也有自己一些不一样的特点，k8s是谷歌公司根据自身的多年的运维经验研发的一款容器管理平台。而Docker Swarm则是由Docker 公司研发的。

总结
Docker是容器技术的核心、基础，Docker Compose是一个基于Docker的单主机容器编排工具.而k8s是一个跨主机的集群部署工具，功能并不像Docker Swarm和Kubernetes是基于Dcoker的跨主机的容器管理平台那么丰富


```

