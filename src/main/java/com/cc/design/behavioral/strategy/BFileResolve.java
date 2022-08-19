package com.cc.design.behavioral.strategy;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BFileResolve implements IFileStrategy {
    private  final Logger logger = LoggerFactory.getLogger(BFileResolve.class);

    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.File_B_RESOLVE;
    }

    @Override
    public void resolve(Object objectparam) {
        logger.info("B 类型解析文件，参数：{}",objectparam);
        //A类型解析具体逻辑
    }
}