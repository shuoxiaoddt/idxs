package com.micro.service.webmvc.utils;

import com.micro.service.webmvc.annotation.TransactionalXS;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by xiaos 2018/6/6
 * //TODO 写点注释吧
 */
@Slf4j
@Component
public class MyCglibAopProxy implements MethodInterceptor {

    @Autowired
    private DataSource dataSource;

    public Enhancer enhancer = new Enhancer();



    public MyCglibAopProxy(){

    }
    public Object  getInstance(Class cls){
        enhancer.setSuperclass(cls);
        enhancer.setCallbacks(new Callback[]{this, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new TransactionalFilter());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("调用方法:start"+method.getName());
        StopWatch stopWatch = new StopWatch(method.getName());
        stopWatch.start("start invoke method");
        TransactionalXS annotation = method.getAnnotation(TransactionalXS.class);
        Object result = null;
        if(!Objects.isNull(annotation)){
            DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
            platformTransactionManager.setDataSource(dataSource);
            TransactionStatus transaction = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            Object savepoint =  transaction.createSavepoint();
            try{
                result = methodProxy.invokeSuper(o, objects);
            }catch (Exception e){
                e.printStackTrace();
                transaction.rollbackToSavepoint(savepoint);
            }finally {
            }
        }else{
            result = methodProxy.invokeSuper(o, objects);
        }
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return result;
    }

}
