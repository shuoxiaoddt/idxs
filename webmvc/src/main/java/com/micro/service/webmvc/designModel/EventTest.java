package com.micro.service.webmvc.designModel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xiaos 2018/6/22
 */
public class EventTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.micro.service.webmvc");
        //设置监听器
        context.addApplicationListener(new SayHelloEventListener());
        //context.addApplicationListener(new MContextRefreshedListener());
        context.refresh();
        //发布事件
        context.publishEvent(new SayHelloEvent("hello!!"));
    }
}
