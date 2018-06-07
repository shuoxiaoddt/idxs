package com.micro.service.webmvc.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.StopWatch;

import java.util.concurrent.Future;

/**
 * Created by xiaos 2018/6/5
 * //TODO 写点注释吧
 */
@Component
@Slf4j
public class AsyncUtil {
    @Async
    public Future<String> task1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start("task1->"+Thread.currentThread().getName());
        Thread.sleep(1000);
        stopWatch.stop();
        return new AsyncResult("task1->"+Thread.currentThread().getName());
    }
    @Async
    public Future<String> task2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start("task2->"+Thread.currentThread().getName());
        Thread.sleep(1000);
        log.info(stopWatch.prettyPrint());
        return new AsyncResult("task2->"+Thread.currentThread().getName());
    }
    @Async
    public Future<String> task3() throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start("task3->"+Thread.currentThread().getName());
        Thread.sleep(1000);
        stopWatch.stop();
        return new AsyncResult("task3->"+Thread.currentThread().getName());
    }
}
