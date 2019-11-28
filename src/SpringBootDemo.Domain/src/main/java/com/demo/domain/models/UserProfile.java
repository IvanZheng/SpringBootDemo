package com.demo.domain.models;

import java.io.Serializable;

public class UserProfile{
    private Address address;
    private String hobby;
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
