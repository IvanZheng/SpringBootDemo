package com.demo.portal.controllers;

import com.demo.domain.models.User;
import com.demo.portal.message.CancelOrder;
import com.demo.portal.message.SubmitOrder;
import com.demo.portal.rabbitmq.Message;
import com.demo.portal.rabbitmq.Producer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    private final Producer producer;

    public RabbitController(Producer producer) {
        this.producer = producer;
    }

    @RequestMapping(value = "send")
    public Message send(@RequestParam("type") String type) {
        Message message = "submit".equals(type) ? new SubmitOrder() : new CancelOrder();
        producer.send(message);
        return message;
    }
}
