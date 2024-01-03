package com.demo.portal.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue recordQueue(@Value("${rabbit.queue.record}") String queue){
        return new Queue(queue);
    }

    @Bean
    public Queue configQueue(@Value("${rabbit.queue.config}") String queue){
        return new Queue(queue);
    }

    @Bean
    public DirectExchange exchange(@Value("${rabbit.exchange.demo}") String exchange){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding deviceRecordBinding(DirectExchange exchange, Queue recordQueue, @Value("${rabbit.routingKey.record}") String record){
        return BindingBuilder.bind(recordQueue).to(exchange).with(record);
    }

    @Bean
    public Binding deviceConfigBinding(DirectExchange exchange, Queue configQueue, @Value("${rabbit.routingKey.config}") String config){
        return BindingBuilder.bind(configQueue).to(exchange).with(config);
    }
}
