package com.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String gender;

    @AttributeOverrides({
            @AttributeOverride(name="address.country",
                    column=@Column(name="userProfile_AddressCountry")),
            @AttributeOverride(name="address.city",
                    column=@Column(name="userProfile_AddressCity")),
            @AttributeOverride(name="address.street",
                    column=@Column(name="userProfile_AddressStreet")),
            @AttributeOverride(name="hobby",
                    column=@Column(name="userProfile_hobby")),
    })
    private UserProfile userProfile;

    @AttributeOverrides({
            @AttributeOverride(name="address.country",
                    column=@Column(name="userProfile2_AddressCountry")),
            @AttributeOverride(name="address.city",
                    column=@Column(name="userProfile2_AddressCity")),
            @AttributeOverride(name="address.street",
                    column=@Column(name="userProfile2_AddressStreet")),
            @AttributeOverride(name="hobby",
                    column=@Column(name="userProfile2_hobby")),
    })
    private UserProfile userProfile2;

    @ElementCollection
    private List<String> numbers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Card> cards = new ArrayList<>();

    @Version
    @Type(type = "timestamp")
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
        //cards.forEach(card -> card.setUserId(getId()));
        this.cards = cards;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public String getGender() {
        return gender;
    }

    public Date getVersion() {
        return version;
    }

    public UserProfile getUserProfile2() {
        return userProfile2;
    }

    protected void setUserProfile2(UserProfile userProfile2) {
        this.userProfile2 = userProfile2;
    }

//    protected void setVersion(Timestamp version) {
//        this.version = version;
//    }
}

