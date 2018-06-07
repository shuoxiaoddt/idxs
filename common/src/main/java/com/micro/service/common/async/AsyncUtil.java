package com.micro.service.common.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.Future;

/**
 * Created by xiaos 2018/6/5
 * //TODO 写点注释吧
 */
@Component
@Slf4j
public class AsyncUtil {

    public Future<String> task1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("task1");
        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new AsyncResult("task1");
    }
    public Future<String> task2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("task2");
        stopWatch.start("task2");
        Thread.sleep(1000);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new AsyncResult("task2");
    }
    public Future<String> task3() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("task3");
        stopWatch.start("task3");
        Thread.sleep(1000);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new AsyncResult("task3");
    }
}
