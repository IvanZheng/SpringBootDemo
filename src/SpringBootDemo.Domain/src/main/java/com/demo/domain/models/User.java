package com.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"handler"})
public class User {
    private String id;
    private String name;
    private String gender;
    private UserProfile userProfile;
    private List<Card> cards;

    public User() {
    }

    public User(String name, String gender, UserProfile userProfile, List<Card> cards) {
        setId(UUID.randomUUID().toString());
        this.name = name;
        this.gender = gender;
        this.userProfile = userProfile;
        setCards(cards);
    }

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getGender() {
        return gender;
    }

    protected void setGender(String gender) {
        this.gender = gender;
    }

    protected void setCards(List<Card> cards) {
        cards.forEach(card -> card.setUserId(getId()));
        this.cards = cards;
    }
}

