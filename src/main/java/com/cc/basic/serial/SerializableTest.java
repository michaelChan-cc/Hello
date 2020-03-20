/**
 * @file: SerializableTest
 * @author: michael
 * @date: 2020/3/20 16:15
 * @copyright: 南京凯盛
 */
package com.cc.basic.serial;

import java.io.*;

/**
 *  序列化
 *
 *  WHAT： 序列化是指把对象转换为字节序列的过程，我们称之为对象的序列化，就是把内存中的这些对象变成一连串的字节(bytes)描述的过程。
 *  WHY:  表示对象及其数据的类型信息和字节在内存中重新创建对象
 *          底层IO操作都是以字节流的方式进行的，所以写操作都涉及将编程语言数据类型转换为字节流，
 *          而读操作则又涉及将字节流转化为编程语言类型的特定数据类型。
 *          而Java作为一门面向对象的编程语言，对象作为其主要数据的类型载体，为了完成对象数据的读写操作，也就需要一种方式来让JVM知道在进行IO操作时如何将对象数据转换为字节流，以及如何将字节流数据转换为特定的对象，而Serializable接口就承担了这样一个角色。
 *  HOW： IO对象必备实现对象复制，否则java.io.NotSerializableException: com.cc.basic.serial.SerializableTest
 *
 *  使用场景： 1 例如我们利用mybatis框架编写持久层insert对象数据到数据库中时
 *          2  网络通信时需要用套接字在网络中传送对象时，如我们使用RPC协议进行网络通信时;
 *
 * @author michael
 * @version 1.0 Created on 2020/3/20 16:15
 */
public class SerializableTest implements Serializable {

    /**
     * 对于JVM来说，要进行持久化的类必须要有一个标记，
     * 只有持有这个标记JVM才允许类创建的对象可以通过其IO系统转换为字节数据，从而实现持久化，而这个标记就是Serializable接口。
     * 而在反序列化的过程中则需要使用serialVersionUID来确定由那个类来加载这个对象
     *
     * 隐性声明-》JVM默认生成serialVersionUID， 可能会导致其与编译器的实现细节耦合，这样可能会导致在反序列化的过程中发生意外的InvalidClassException异常
     *
     * 有个特殊的地方需要注意的是，数组类是不能显示地声明serialVersionUID的，因为它们始终具有默认计算的值，不过数组类反序列化过程中也是放弃了匹配serialVersionUID值的要求
     *
     */
    private static final long serialVersionUID = 1L;

    private int a = 23;

    private String b = "acd";

    public byte[] bytes;

    public SerializableTest(int a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "a: "+ a +" ; b: " + b;
    }

    /**
     * 将对象序列化后， 写入流文件
     * @throws IOException
     */
    public static void writeObj() throws IOException {
        SerializableTest source = new SerializableTest(2, "QW小EEE");
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("/serial.text"));

        stream.writeObject(source);
        stream.close();
    }

    /**
     * 读取流文件， 反向序列化成一个对象
     * @throws IOException
     */
    public static void readObj() throws ClassNotFoundException, IOException {
//        SerializableTest source = new SerializableTest(2, "QWEEE");
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("/serial.text"));

        SerializableTest rst = (SerializableTest) stream.readObject();
        stream.close();

        System.out.println(rst);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeObj();

        readObj();
    }

}
