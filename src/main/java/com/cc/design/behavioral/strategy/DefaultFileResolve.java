package com.cc.design.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultFileResolve implements IFileStrategy{
    private  final Logger logger = LoggerFactory.getLogger(DefaultFileResolve.class);

    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.File_DEFAULT_RESOLVE;
    }

    @Override
    public void resolve(Object objectparam) {
        logger.info("默认类型解析文件，参数：{}",objectparam);
        //默认类型解析具体逻辑
    }
}
