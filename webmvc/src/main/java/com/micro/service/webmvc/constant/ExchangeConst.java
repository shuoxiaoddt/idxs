package com.micro.service.webmvc.constant;

/**
 * Created by xiaos 2018/6/25
 * AMPQ exchange常量
 */
public interface ExchangeConst {
    /**
     * 配置默认交换机
     **/
    String DEFAULT_EXCHANGE = "fb4.exchange";
    /**
     * 配置死信交换机
     **/
    String DEAD_EXCHANGE = "fb4.dead.exchange";
    /**
     * DLX QUEUE
     **/
    String DEAD_LETTER_QUEUE_NAME = ".dead";
    /**
     * X_MESSAGE_TTL
     * */
    Long X_MESSAGE_TTL = 60000L;
}
