package com.micro.service.webmvc.designModel;

import org.springframework.context.ApplicationEvent;

/**
 * Created by xiaos 2018/6/22
 */
public class SayHelloEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SayHelloEvent(Object source) {
        super(source);
    }
}
