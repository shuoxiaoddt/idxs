package com.micro.service.webmvc.constant;

import lombok.Getter;

/**
 * Created by xiaos 2018/6/25
 * //TODO 写点注释吧
 */
public enum  SendSynQueueConst {

    WAREHOUSE_SYN_SEND_WAREHOUSE_ASSORT_JOB("idxs.first.queue");

    @Getter
    private String queueName;

    SendSynQueueConst(String queueName) {
        this.queueName = queueName;
    }
}
