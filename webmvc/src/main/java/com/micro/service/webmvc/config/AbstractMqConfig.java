package com.micro.service.webmvc.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.service.webmvc.constant.ExchangeConst;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaos 2018/6/25
 */
@Data
public abstract class AbstractMqConfig {
    @Getter
    private final Map<String,Queue> queueMap = Maps.newConcurrentMap();

    protected void init(List<String> queueNames){
        queueNames.forEach(queueName -> {
            String deadQueueName =queueName + ExchangeConst.DEAD_LETTER_QUEUE_NAME;
            Queue messageQueue =QueueBuilder.durable(queueName)
                    .withArgument("x-dead-letter-exchange",ExchangeConst.DEAD_EXCHANGE)
                    .withArgument("x-dead-letter-routing-key",deadQueueName)
                    .build();
            Queue deadQueue = QueueBuilder.durable(deadQueueName)
                    .withArgument("x-dead-letter-exchange",ExchangeConst.DEFAULT_EXCHANGE)
                    .withArgument("x-dead-letter-routing-key",queueName)
                    .withArgument("x-message-ttl",ExchangeConst.X_MESSAGE_TTL)
                    .build();
            queueMap.put(queueName,messageQueue);
            queueMap.put(deadQueueName,deadQueue);
        });
    }
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(ExchangeConst.DEFAULT_EXCHANGE);
    }
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(ExchangeConst.DEAD_EXCHANGE);
    }

    @Bean
    public List<Queue> queues() {
        return Lists.newArrayList(queueMap.values());
    }
    @Bean
    public List<Binding> binding(){
        List<Binding> bindings = Lists.newArrayListWithCapacity(queueMap.size());
        Set<Map.Entry<String, Queue>> entries = queueMap.entrySet();
        Iterator<Map.Entry<String, Queue>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Queue> next = iterator.next();
            Queue queue = next.getValue();
            String queueName = next.getKey();
            DirectExchange exchange = defaultExchange();
            if(StringUtils.contains(queueName,ExchangeConst.DEAD_EXCHANGE)){
                exchange = deadExchange();
            }
            Binding binding =BindingBuilder.bind(queue).to(exchange).with(queueName);
            bindings.add(binding);
        }
        return bindings;
    }
    @Bean
    public SimpleRabbitListenerContainerFactory myFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer ,
                                                          ConnectionFactory connectionFactory , @Value("${idxs.rabbitmq.concurrency:2}") int concurrentConsumers){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        factory.setConcurrentConsumers(concurrentConsumers);
        return factory;
    }
}
