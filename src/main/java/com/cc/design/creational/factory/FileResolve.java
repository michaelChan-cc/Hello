package com.cc.design.creational.factory;

public class FileResolve {
}

class AFileResolve implements IFileResolveFactory{
    @Override
    public void resolve(){
        System.out.println("文件A类型解析");
    }
}

class BFileResolve implements IFileResolveFactory{
    @Override
    public void resolve(){
        System.out.println("文件B类型解析");
    }
}

class DefaultFileResolve implements IFileResolveFactory{
    @Override
    public void resolve(){
        System.out.println("默认文件类型解析");
    }
}