package com.demo.portal.message;

import com.demo.portal.rabbitmq.Message;
import com.demo.portal.rabbitmq.Topic;

@Topic(exchange = "demo", routingKey = "record")
public class SubmitOrder extends Message {

}
