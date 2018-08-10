package com.micro.service.webmvc.config;

import com.google.common.collect.Lists;
import com.micro.service.webmvc.constant.SendSynQueueConst;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaos 2018/6/22
 * Rabbmq 配置
 */
@Configuration
public class RabbitMQConfig extends AbstractMqConfig{

    private static List<String> sendSynQueues =
            Lists.newArrayList(SendSynQueueConst.values()).stream().
                    map(SendSynQueueConst::getQueueName).collect(Collectors.toList());

    @PostConstruct
    public void init(){

        super.init(sendSynQueues);
    }

}
