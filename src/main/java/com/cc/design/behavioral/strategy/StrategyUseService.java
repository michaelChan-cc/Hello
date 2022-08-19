package com.cc.design.behavioral.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StrategyUseService implements ApplicationContextAware {

    private Map<FileTypeResolveEnum, IFileStrategy> imap = new ConcurrentHashMap<>();

    public void resolveFile(FileTypeResolveEnum fileTypeResolveEnum, Object objectParam){
        IFileStrategy strategy = imap.get(fileTypeResolveEnum);
        if (strategy != null){
            strategy.resolve(objectParam);
        }
    }

    /**
     * //把不同策略放到map
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IFileStrategy> iFileStrategyMap =  applicationContext.getBeansOfType(IFileStrategy.class);
        iFileStrategyMap.values().forEach(iFileStrategy -> imap.put(iFileStrategy.gainFileType(), iFileStrategy));
    }

}
