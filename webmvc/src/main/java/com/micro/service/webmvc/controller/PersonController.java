package com.micro.service.webmvc.controller;

import com.micro.service.webmvc.async.AsyncUtil;
import com.micro.service.webmvc.entity.Person;
import com.micro.service.webmvc.service.SpringTransactional;
import com.micro.service.webmvc.utils.MyCglibAopProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 写点注释吧!!
 *
 * @author xiaos
 * @date : 2018/5/9
 */
//@RestController
//@RequestMapping("person")
@Slf4j
public class PersonController {

    @Resource
    private SpringTransactional springTransactional;

    @Resource
    private AsyncUtil asyncUtil;
    @PostMapping(value = "save")
    public Person savae(){
        springTransactional.query();
        return new Person();
    }

    @PostMapping(value = "personPropertiesToJson",
            consumes = "application/properties+person",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person personPropertiesToJson(@RequestBody Person person){

        return person;
    }

    @PostMapping(value = "personJsonToProperties",
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                produces = "application/properties+person" )
    public Person personJsonToProperties(@RequestBody Person person){
            return person;
    }
    @GetMapping("/addPerson")
    public Person addPerson(@Valid Person person , BindingResult bindingResult){

        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            System.out.println(allErrors.get(0).getDefaultMessage());
        }
        return person;
    }
    @Async
    public Future<String> asyncInClass() throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start("asyncInClass->"+Thread.currentThread().getName());
        Thread.sleep(1000);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new AsyncResult("asyncInClass->"+Thread.currentThread().getName());
    }

    @PostMapping(value = "asyncTest")
    public Person asyncTest(@RequestBody Person person) throws Exception{
        Future<String> task1 = asyncUtil.task1();
        Future<String> task2 = asyncUtil.task2();
        Future<String> task3 = asyncUtil.task3();
        PersonController personController = new PersonController();
        Future<String> asyncInClass = personController.asyncInClass();
        for (;;) {
            if(task1.isDone() && task2.isDone() && task3.isDone() && asyncInClass.isDone()) {
                // 三个任务都调用完成，退出循环等待
                System.out.println(task1.get());
                System.out.println(task2.get());
                System.out.println(task3.get());
                System.out.println(asyncInClass.get());
                break;
            }
            Thread.sleep(1000);
        }
        System.err.println(Thread.currentThread().getContextClassLoader()+"====================END====================");
        return person;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext();
        applicationContext.scan("com.micro.service.webmvc");
        applicationContext.refresh();
        MyCglibAopProxy cglibAopProxy = applicationContext.getBean("myCglibAopProxy",MyCglibAopProxy.class);
        SpringTransactional springTransactional =
                (SpringTransactional)cglibAopProxy.getInstance(SpringTransactional.class);
        SpringTransactional container = applicationContext.getBean("springTransactional",SpringTransactional.class);
        container.add();
        springTransactional.add();

    }

}
