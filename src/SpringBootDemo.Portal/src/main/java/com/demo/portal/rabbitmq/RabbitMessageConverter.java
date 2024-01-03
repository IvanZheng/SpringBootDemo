package com.demo.portal.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageConverter implements MessageConverter {
    private final Jackson2JsonMessageConverter jsonConverter;
    public RabbitMessageConverter(ObjectMapper objectMapper) {
        this.jsonConverter = new Jackson2JsonMessageConverter(objectMapper);
    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        var messageType = o.getClass().getName();
        messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, messageType);
        return jsonConverter.toMessage(o, messageProperties);
    }


    @SneakyThrows
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        MessageProperties properties = message.getMessageProperties();
        String type = properties.getHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME);
        if (type != null) {
            var javaType = Class.forName(type);
            return jsonConverter.fromMessage(message, ParameterizedTypeReference.forType(javaType));
        } else {
            return jsonConverter.fromMessage(message);
        }
    }
}
