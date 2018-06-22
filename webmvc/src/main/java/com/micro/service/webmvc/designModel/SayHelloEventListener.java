package com.micro.service.webmvc.designModel;

import org.springframework.context.ApplicationListener;

/**
 * Created by xiaos 2018/6/22
 * //TODO 写点注释吧
 */
public class SayHelloEventListener implements ApplicationListener<SayHelloEvent> {

    @Override
    public void onApplicationEvent(SayHelloEvent event) {
        Object source = event.getSource();
        System.out.println(source);
    }
}
