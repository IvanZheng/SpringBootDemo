package com.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class User implements Serializable {
    private String id;
    private String name;
    private List<Card> cards;

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

    public List<Card> getCards() {
        return cards;
    }
}
