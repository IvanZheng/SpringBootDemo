package com.demo.portal.rabbitmq;

import lombok.var;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate template;
    public Producer(RabbitTemplate rabbitTemplate, RabbitMessageConverter messageConverter) {
        rabbitTemplate.setMessageConverter(messageConverter);
        this.template = rabbitTemplate;
    }

    public void send(Message message) throws AmqpException {
        var topic = message.getClass().getAnnotation(Topic.class);
        var exchange = topic.exchange();
        var routingKey = topic.routingKey();
        template.convertAndSend(exchange, routingKey, message);
    }
}
