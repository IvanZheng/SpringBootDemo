package com.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"handler"})
public class User {
    private String id;
    private String name;
    private String gender;
    private UserProfile userProfile;
    private List<Card> cards;
    private Timestamp Version;

    public User() {
    }

    public User(String name, String gender, UserProfile userProfile, List<Card> cards) {
        setId(UUID.randomUUID().toString());
        this.name = name;
        this.gender = gender;
        this.userProfile = userProfile;
        setCards(cards);
    }

    public void update(String name, String gender, UserProfile userProfile)
    {
        setName(name);
        setGender(gender);
        setUserProfile(userProfile);
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

    protected void setUserProfile(UserProfile userProfile) {
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

    public Timestamp getVersion() {
        return Version;
    }

    private void setVersion(Timestamp version) {
        Version = version;
    }
}

