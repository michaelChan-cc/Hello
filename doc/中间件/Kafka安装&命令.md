Kafka

- [官方文档](https://kafka.apache.org)
- [部署参考文档](https://blog.csdn.net/weixin_39984161/article/details/91971731)





# 1.系统基础插件

```
yum install -y lrzsz



```

# 2.下载包，解压、配置

[root@iZbp1hsnehtjzo7neeopkdZ cc]# tar -zxvf kafka_2.13-3.0.0.tgz 





# 3.启动命令

```
/root/cc/kafka_2.13-3.0.0/bin/zookeeper-server-start.sh -daemon /root/cc/kafka_2.13-3.0.0/config/zookeeper.properties
/root/cc/kafka_2.13-3.0.0/bin/zookeeper-server-stop.sh -daemon /root/cc/kafka_2.13-3.0.0/config/zookeeper.properties

/root/cc/kafka_2.13-3.0.0/bin/kafka-server-start.sh -daemon /root/cc/kafka_2.13-3.0.0/config/server.properties
/root/cc/kafka_2.13-3.0.0/bin/kafka-server-stop.sh /root/cc/kafka_2.13-3.0.0/config/server.properties


sudo lsof -i:2181
kill -9 7789

```



# 4.topic



```shell
在较新版本（2.2 及更高版本）的 Kafka 不再需要 ZooKeeper 连接字符串，即- -zookeeper localhost:2181。使用 Kafka Broker的 --bootstrap-server localhost:9092来替代- -zookeeper localhost:2181。

[root@iZbp1hsnehtjzo7neeopkdZ kafka_2.13-3.0.0]#  bin/kafka-topics.sh --bootstrap-server 116.62.210.214:9092 --create --topic cctest --partitions 2 --replication-factor 1
Created topic cctest.


> bin/kafka-configs.sh --bootstrap-server 116.62.210.214:9092 --entity-type topics --entity-name cctest --describe

bin/kafka-console-producer.sh --topic cctest --bootstrap-server 116.62.210.214:9092
bin/kafka-console-consumer.sh --topic cctest --from-beginning --bootstrap-server localhost:9092



```



# 5.kafka-console-ui 

```
wget https://github.com/xxd763795151/kafka-console-ui/releases/download/v1.0.1/kafka-console-ui.tar.gz


# 解压缩(以tar.gz为例)
tar -zxvf kafka-console-ui.tar.gz
# 进入解压缩后的目录
cd kafka-console-ui
# 编辑配置
vim config/application.yml
# 启动
sh bin/start.sh
# 停止
sh bin/shutdown.sh

访问地址： http://116.62.210.214:7766/#/
```

