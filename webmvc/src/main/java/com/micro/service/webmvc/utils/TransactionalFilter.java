package com.micro.service.webmvc.utils;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by xiaos 2018/6/6
 * //TODO 写点注释吧
 */
public class TransactionalFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if(method.getName().equals("add")){
            return 0;
        }
        return 1;
    }
}
