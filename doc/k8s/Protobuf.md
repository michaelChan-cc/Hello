# Protobuf 使用指南

## 一、简介

最近在手撸 IM 系统，关于数据传输格式的选择，犹豫了下，对比了 JSON 和 XML，最后选择了 Protobuf 作为数据传输格式。

毕竟 Google 出品，必属精品😂，[[官网地址\]](https://links.jianshu.com/go?to=https%3A%2F%2Fdevelopers.google.com%2Fprotocol-buffers%2Fdocs%2Fproto3)。
 好了，舔狗环节结束，关于技术选择，都是需要根据实际的应用场景的，否则都是耍流氓，下文会进行简单的对比，先来看看官网的介绍：

> 他是一种与语言无关、与平台无关，是一种可扩展的用于序列化和结构化数据的方法，常用于用于通信协议，数据存储等。
>  他是一种灵活，高效，自动化的机制，用于序列化结构化数据，对比于 XML，他更小（310倍），更快（20100倍），更简单。

当然，最简单粗暴的理解方式，就是结合 JSON 和 XML 来理解，你可以暂时将他们仨理解成同一种类型的事物，但是呢，Protobuf  对比于他们两个，拥有着体量更小，解析速度更快的优势，所以，在 IM 这种通信应用中，非常适合将 Protobuf 作为数据传输格式。

## 二、关于 proto3

Protobuf 有两个大版本，proto2 和 proto3，同比 python 的 2.x 和 3.x 版本，如果是新接触的话，同样建议直接入手 proto3 版本。所以下文的描述都是基于 proto3 的。

proto3 相对 proto2 而言，简言之就是支持更多的语言（Ruby、C#等）、删除了一些复杂的语法和特性、引入了更多的约定等。

> 为什么要关注语言，因为它不像 JSON 一样开箱即用，它依赖工具包来进行编译成 java 文件或 go 文件等。

正如硬币的两面性一样，凡事皆有双面性，Protobuf 数据的体量更小，所以自然失去了人类的直接可读性， JSON  数据结构是可以很直观地阅读的，但是 Protobuf 我们需要借助工具来进行更友好地使用，所以，我们需要自定义一个 schema  来定义数据结构的描述，即下面的 message。

- Message

举个很简单的栗子，摘自官网：

```proto
syntax = "proto3"; // proto3 必须加此注解

message SearchRequest {
  string query = 1;
  int32 page_number = 2;
  int32 result_per_page = 3;
  enum Corpus {
    UNIVERSAL = 0;
    WEB = 1;
    IMAGES = 2;
    LOCAL = 3;
    NEWS = 4;
    PRODUCTS = 5;
    VIDEO = 6;
  }
  Corpus corpus = 4;
}
```

上面便是定义好的一个 message，里面包含：

1. String 类型的 query，编号是 1 **（注：字段必须有编号且编号不允许重复）**
2. int 类型的 page_number，编号是 2
3. 枚举类型的 corpus **（注：枚举内部的编号也不允许重复，并且第一个编号必须为0）**

## 三、对比 JSON 和 XML

![img](https://upload-images.jianshu.io/upload_images/16538952-ab055e1126a3942b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

对比图

## 四、应用

此处以 Windows 为例，其他的都差不多。

1. windows 安装

- protoc 下载：[[官方下载地址\]](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fprotocolbuffers%2Fprotobuf%2Freleases%2Ftag%2Fv3.7.1)，然后将 bin 路径添加到 path 环境变量下去
- 查看是否安装成功：控制台输入 `protoc --version` ，控制台输出版本信息代表成功，如： `libprotoc 3.7.1`

1. ideal 安装插件

- ideal 插件库搜索安装 **Protobuf Support** 即可
- 此插件可以不用安装，但是这有助于一些源码阅读的便利性和一些编码提示

> IDE 最大的作用不就是快速编码嘛

1. 编写 proto 文件
    定义一个 JetProtos.proto 文件

```proto
syntax = "proto3"; // PB协议版本

import "google/protobuf/any.proto"; // 引用外部的message，可以是本地的，也可以是此处比较特殊的 Any

package jet.protobuf; // 包名，其他 proto 在引用此 proto 的时候，就可以使用 test.protobuf.PersonTest 来使用，
// 注意：和下面的 java_package 是两种易混淆概念，同时定义的时候，java_package 具有较高的优先级

option java_package = "com.jet.protobuf"; // 生成类的包名，注意：会在指定路径下按照该包名的定义来生成文件夹
option java_outer_classname="PersonTestProtos"; // 生成类的类名，注意：下划线的命名会在编译的时候被自动改为驼峰命名

message PersonTest {  
    int32 id = 1; // int 类型  
    string name = 2; // string 类型  
    string email = 3;  
    Sex sex = 4; // 枚举类型  
    repeated PhoneNumber phone = 5; // 引用下面定义的 PhoneNumber 类型的 message  
    map<string, string> tags = 6; // map 类型  
    repeated google.protobuf.Any details = 7; // 使用 google 的 any 类型  

    // 定义一个枚举  
    enum Sex {      
        DEFAULT = 0;      
        MALE = 1;      
        Female = 2;  
    }  
    
    // 定义一个 message  
    message PhoneNumber {    
        string number = 1;    
        PhoneType type = 2;    
        
        enum PhoneType {      
            MOBILE = 0;      
            HOME = 1;      
            WORK = 2;    
        }  
        
    }
    
}
```

1. 编译成 java 文件
    进入 proto 文件所在路径，输入下面 protoc 命令（后面有三部分参数），然后将编译得出的 java 文件拷贝到项目中即可（此 java 文件可以理解成使用的数据对象）：

```undefined
protoc -I=./ --java_out=./ ./JetProtos.proto
或
protoc -proto_path=./ --java_out=./ ./JetProtos.proto
```

> 参数说明：
>
> 1. -I 等价于 -proto_path：指定 .proto 文件所在的路径
> 2. --java_out：编译成 java 文件时，标明输出目标路径
> 3. ./JetProtos.proto：指定需要编译的 .proto 文件

1. 使用

- maven 引入指定包

```xml
<!-- protobuf -->
<dependency>     
    <groupId>com.google.protobuf</groupId>     
    <artifactId>protobuf-java</artifactId>     
    <version>3.7.1</version>
</dependency>
```

- 使用
   序列化和反序列化有多种方式，可以是 byte[]，也可以是 inputStream 等，

```java
package com.jet.mini.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName: ProtoTest
 * @Description: ProtoBuf 测试
 * @Author: Jet.Chen
 * @Date: 2019/5/8 9:55
 * @Version: 1.0
 **/
public class ProtoTest {

    public static void main(String[] args) {
        try {
            /** Step1：生成 personTest 对象 */
            // personTest 构造器
            PersonTestProtos.PersonTest.Builder personBuilder = PersonTestProtos.PersonTest.newBuilder();
            // personTest 赋值
            personBuilder.setName("Jet Chen");
            personBuilder.setEmail("ckk505214992@gmail.com");
            personBuilder.setSex(PersonTestProtos.PersonTest.Sex.MALE);

            // 内部的 PhoneNumber 构造器
            PersonTestProtos.PersonTest.PhoneNumber.Builder phoneNumberBuilder = PersonTestProtos.PersonTest.PhoneNumber.newBuilder();
            // PhoneNumber 赋值
            phoneNumberBuilder.setType(PersonTestProtos.PersonTest.PhoneNumber.PhoneType.MOBILE);
            phoneNumberBuilder.setNumber("17717037257");

            // personTest 设置 PhoneNumber
            personBuilder.addPhone(phoneNumberBuilder);

            // 生成 personTest 对象
            PersonTestProtos.PersonTest personTest = personBuilder.build();

            /** Step2：序列化和反序列化 */
            // 方式一 byte[]：
            // 序列化
//            byte[] bytes = personTest.toByteArray();
            // 反序列化
//            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(bytes);
//            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));



            // 方式二 ByteString：
            // 序列化
//            ByteString byteString = personTest.toByteString();
//            System.out.println(byteString.toString());
            // 反序列化
//            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(byteString);
//            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));



            // 方式三 InputStream
            // 粘包,将一个或者多个protobuf 对象字节写入 stream
            // 序列化
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            personTest.writeDelimitedTo(byteArrayOutputStream);
            // 反序列化，从 steam 中读取一个或者多个 protobuf 字节对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseDelimitedFrom(byteArrayInputStream);
            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
```

## 五、message 部分语法说明

1. 在 proto3 中，枚举的第一个常量名的编号必须为 0
    在 proto3 中，由于默认值的规则进行了调整，而枚举的默认值为第一个，所以必须将第一个常量的编号置为 0，但是这与我们的业务有时候是有冲突的，所以，我们常将第一个常量设为：xx_UNSPECIFIED = 0，如：`ENUM_TYPE_UNSPECIFIED = 0；`，当然这不是我们自己约定的，这是 Google API Guilder 中建议的。
2. 同一个 proto 文件中，多个枚举之间不允许定义相同的常量名
    如下面的 message 在编译的时候就会报错 `IDEA is already defined in "xxx"`：

```rust
enum IDE1 {
    IDEA = 0;
    ECLIPSE = 1;
}

enum IDE2 {
    IDEA = 7;
    ECLIPSE = 8;
}
```

1. 关于数据类型匹配
    见下图，摘自官网：

   ![img](https://upload-images.jianshu.io/upload_images/16538952-d4f48d42b5d4c34f.png?imageMogr2/auto-orient/strip|imageView2/2/w/1187/format/webp)

   Protobuf 数据类型参考图

2. 关于默认值
    proto3 中，数据的默认值不再支持自定义，而是由程序自行推倒：

- string：默认值为空
- bytes：默认值为空
- bools：默认值为 false
- 数字类型：默认值为 0
- 枚举类型： 默认为定义的第一个元素，并且编号必须为 0
- message 类型：默认值为 DEFAULT_INSTANCE，其值相当于空的 message

## 六、总结

1. XML、JSON、Protobuf 都具有数据结构化和数据序列化的能力
2. XML、JSON 更注重 **数据结构化**，关注人类可读性和语义表达能力。Protobuf 更注重 **数据序列化**，关注效率、空间、速度，人类可读性差，语义表达能力不足
3. Protobuf 的应用场景更为明确，XML、JSON 的应用场景更为丰富