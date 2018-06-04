package com.micro.service.webmvc.config;

import com.micro.service.webmvc.messageCovert.PersonMessageCovert;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 写点注释吧!!
 *
 * @author xiaos
 * @date : 2018/5/14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PersonMessageCovert());
    }
}
