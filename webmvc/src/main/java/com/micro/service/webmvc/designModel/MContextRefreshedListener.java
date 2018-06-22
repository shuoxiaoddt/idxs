package com.micro.service.webmvc.designModel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by xiaos 2018/6/22
 * //TODO 写点注释吧
 */
@Component
public class MContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> , ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.err.println("=============onApplicationEvent==================");
        ApplicationContext context = (ApplicationContext)event.getSource();
         System.out.println(context);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("=============applicationContext>NULL==================");
        if(applicationContext!=null){
            System.err.println("=============setApplicationContext==================");
            this.applicationContext = applicationContext;
        }
    }
}
