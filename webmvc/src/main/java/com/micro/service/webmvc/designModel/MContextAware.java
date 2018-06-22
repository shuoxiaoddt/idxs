package com.micro.service.webmvc.designModel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by xiaos 2018/6/22
 * //TODO 写点注释吧
 */
@Component
public class MContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("========applicationContext======="+applicationContext);
        this.applicationContext = applicationContext;
    }
}
