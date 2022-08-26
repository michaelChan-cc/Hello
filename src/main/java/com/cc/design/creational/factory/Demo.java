package com.cc.design.creational.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    public static void main(String[] args) throws IOException {

        //构造不同的工厂对象
        //识别方法： 工厂方法可通过构建方法来识别， 它会创建具体类的对象， 但以抽象类型或接口的形式返回这些对象。
        IFileResolveFactory fileResolveFactory;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input fileType: ");
        String fileType = reader.readLine();

        if(fileType.equals("A")){
            fileResolveFactory = new AFileResolve();
        }else if(fileType.equals("B")){
            fileResolveFactory = new BFileResolve();
        }else{
            fileResolveFactory = new DefaultFileResolve();
        }

        fileResolveFactory.resolve();
    }
}
