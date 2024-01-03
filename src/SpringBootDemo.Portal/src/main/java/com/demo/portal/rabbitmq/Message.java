package com.demo.portal.rabbitmq;



import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Message implements Serializable {
    private String id;
    public Message(){
        id = UUID.randomUUID().toString();
    }
}