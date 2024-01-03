package com.demo.portal.message;

import com.demo.portal.rabbitmq.Message;
import com.demo.portal.rabbitmq.Topic;
import org.springframework.context.annotation.Scope;


@Topic(exchange = "demo", routingKey = "record")
public class CancelOrder extends Message {
}
