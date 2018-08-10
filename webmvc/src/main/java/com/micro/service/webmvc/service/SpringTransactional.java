package com.micro.service.webmvc.service;

import com.micro.service.webmvc.annotation.TransactionalXS;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xiaos 2018/6/5
 * //TODO 写点注释吧
 */
@Service
public class SpringTransactional implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    public void query(){
//        SpringTransactional springTransactional = (SpringTransactional) AopContext.currentProxy();
//        System.err.println(this);
//        System.err.println(springTransactional);
//        System.err.println(this == springTransactional);
//        System.err.println(this.hashCode());
//        System.err.println(springTransactional.hashCode());
//        springTransactional.add();
    }
    @TransactionalXS
    public void add(JdbcTemplate jdbcTemplate){
        String sql = "insert into t1(tid,t1str) values (1,'xs')";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
