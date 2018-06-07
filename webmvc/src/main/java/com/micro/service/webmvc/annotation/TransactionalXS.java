package com.micro.service.webmvc.annotation;

import java.lang.annotation.*;

/**
 * Created by xiaos 2018/6/6
 * //TODO 写点注释吧
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalXS {
}
