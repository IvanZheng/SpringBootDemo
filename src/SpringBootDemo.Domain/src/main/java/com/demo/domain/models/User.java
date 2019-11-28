package com.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"handler"})
public class User {
    private String id;
    private String name;
    private String gender;
    private UserProfile userProfile;
    private List<Card> cards;
    private Timestamp version;

    public User() {
    }

    public User(String name, String gender, UserProfile userProfile, List<Card> cards) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.gender = gender;
        this.userProfile = userProfile;
        setCards(cards);
    }

    public void update(String name, String gender, UserProfile userProfile) {
        this.name = name;
        this.gender = gender;
        this.userProfile = userProfile;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public List<Card> getCards() {
        return cards;
    }

    private void setCards(List<Card> cards) {
        cards.forEach(card -> card.setUserId(getId()));
        this.cards = cards;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public String getGender() {
        return gender;
    }

    public Timestamp getVersion() {
        return version;
    }

//    protected void setVersion(Timestamp version) {
//        this.version = version;
//    }
}

