package com.demo.portal.rabbitmq;

import com.demo.portal.message.CancelOrder;
import com.demo.portal.message.SubmitOrder;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("${rabbit.queue.record}"))
public class Consumer {
    @RabbitHandler
    public void process(SubmitOrder message) {
        System.out.println("handle message: " + message);
    }

    @RabbitHandler
    public void process(CancelOrder message) {
        System.out.println("handle message: " + message);
    }
}
