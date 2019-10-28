package com.demo.domain.models;

import java.io.Serializable;

public class Card implements Serializable {
    private String id;
    private String name;
    private String userId;


    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }


    public String getUserId() {
        return userId;
    }

    protected void setUserId(String userId) {
        this.userId = userId;
    }
}
