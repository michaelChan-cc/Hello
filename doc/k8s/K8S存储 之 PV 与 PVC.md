## 前言

K8S存储是通过 卷：Volumn
docker 目录结构：bootfs rootfs 镜像

# 一、k8s 容器磁盘

容器磁盘上的文件的生命周期是短暂的，这就使得在容器中运行重要应用时会出现一些问题。首先，当容器崩溃时，kubelet会重启它，但是容器中的文件将丢供—容器以干净的状态（镜像最初的状态〉重新启动。其次，在Pod中同时运行多个容器时，这些容器之间通常需要共享文件。Kubernetes 中的Volume抽象就很好的解决了这些问题。**Pod中的容器通过Pause容器共享Volume**。

# 二、emptyDir 存储卷

当Pod被分配给节点时，首先创建emptyDir卷，并且只要该Pod在该节点上运行，该卷就会存在。正如卷的名字所述，它最初是空的。Pod中的容器可以读取和写入emptyDir卷中的相同文件，尽管该卷可以挂载到每个容器中的相同或不同路径上。当出于任何原因从节点中删除Pod 时，emptyDir中的数据将被永久删除。

随着pod 的诞生而诞生，pod 一被删除重建，emptyDir 存储卷 数据就会丢失。不是持久化存储到磁盘中的。

用处：pod 临时存储数据。

```shell
mkdir /opt/volumes
cd /opt/volumes

vim pod-emptydir.yaml
```

  - ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: pod-emptydir
      namespace: default
      labels:
        app: myapp
        tier: frontend
    spec:
      containers：
    
      - name: myapp
        image: ikubernetes/myapp: v1
        imagePullPolicy: IfNotPresent
        ports:
        - name: http
          containerPort: 80 #定义容器挂载内容
          volumeMounts:
          #使用的存储卷名称，如果跟下面volume字段name值相同，则表示使用volume的这个存储卷
        - name: html
          #挂载至容器中哪个目录
          mountPath: /usr/share/nginx/html/
      - name：busybox
        image: busybox: latest
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: html
          #在容器内定义挂载存储名称和挂载路径
          mountPath: /data/
          #每两秒往磁盘写时间信息
          command: [ '/bin/sh' , '-c' , 'while true;do echo $(date) >> /data/index.html;sleep 2;done']
          #定义存储卷
          volumes:
          #定义存储卷名称
      - name: html
        #定义存储卷类型
        emptyDir: {}
    
    
    apiVersion: v1
    kind: Pod
    metadata:
      name: pod-emptydir
      namespace: default
      labels:
        app: myapp
        tier: frontend
    spec:
      containers:
    
      - name: myapp
        image: ikubernetes/myapp:v1
        imagePullPolicy: IfNotPresent
        ports:
        - name: http
          containerPort: 80
          volumeMounts:
        - name: html
          mountPath: /usr/share/nginx/html/
      - name: busybox
        image: busybox:latest
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: html
          mountPath: /data/
          command: ['sh','-c','while true;do echo $(date) >> /data/index.html;sleep 2;done']
          volumes:
      - name: html
        emptyDir: {}
    ```
    
    

# 三、PV 和 PVC

PV是由管理员创建的一种存储空间（存储资源）
PVC申请PV的资源

动态资源：自动创建PV资源
静态资源：需要管理员手动从存储服务中创建指定大小的PV

    PersistentVolume（PV）是集群中已由管理员配置的一段网络存储。集群中的资源就像一个节点是一个集群资源。PVv是诸如卷之类的卷插件，但是具有独立于使用PV的任何单个pod的生命周期。
    该API对象捕获存储的实现细节，即NFS，iscSI或云提供商特定的存储系统。
    PersistentVolumeClaim(PvC)是用户存储的请求。Pvc的使用逻辑:在pod中定义一个存储卷（该存储卷类型为FvC)，定义的时候直接指定大小，pvc必须与对应的pv建立关系，pvc会根据定义去pv申请，而pv是由存储空间创建出来的。pv和pvc是kubernetes抽象出来的一种存储资源。
    虽然PersistentVolumeClaims允许用户使用抽象存储资源，但是常见的需求是，用户需要根据不同的需求去创建Pv，用于不同的场景。而此时需要集群管理员提供不同需求的PV，而不仅仅是Pv的大小和访问模式，但又不需要用户了解这些卷的实现细节。
    对于这样的需求，此时可以采用storageClass资源。
    Pv是集群中的资源。PVC是对这些资源的请求，也是对资源的索引检查。PV和PVc之间的相互作用遵循这个生命周期:
    l
    Provisioning(配置) —> Binding(绑定)—>Using(使用) —>Releasing(释放)—>Recycling(回收)
    这里有两种PV的提供方式:静态或者动态
    静态–>直接固定存储空间:
    集群管理员创建一些 PV。它们携带可供集群用户使用的真实存储的详细信息。它们存在于Kubernetes API中，可用于消费。
    在这里插入图片描述
    NFS对外提供的共享目录为V1-V5，k8s管理员会使用 NFS 存储卷的方式创建PV，需要指定存储大小和读写的模式，比如PV1 创建1个G，PV2创建2个G，PV3 创建三个G的空间。这时候创建个Pod，会通过PVC的方式去申请PV资源，比如我这个Pod要2个G的资源，就会去根据读写模式、大小 去匹配 具有相同存储大小和读写的模式的PV，最终创建资源。.
    在这里插入图片描述
    GFS Ceph 分布式文件系统

静态配置PV 步骤

NFS 操作
创建五个目录

# 总结

静态 PV由K8s管理员创建的供k8s集群使用的存储资源，从远程NFS或者分布式存储系统中创建得来，PV有存储空间大小，读写模式
PVC K8s管理员在创建pod时，要为pod申请使用的存储资源的请求，向 PV申请存储资源

StorageClass 是用于动态创建PV供 Pod 使用，相匹配 PVC申请的资源

读写模式（访问模型）：

    Readwriteonce (RWO) 单节点读写
    ReadonlyMany (ROX) 多节点只读
    ReadwriteMany (RWX) 多节点读写
