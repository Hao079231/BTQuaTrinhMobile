package com.example.volleyapi;

import java.io.Serializable;

public class User implements Serializable {
    private int id;

    public User(int id, String name, String email, String gender, String images) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getImages() {
        return images;
    }

    private String name, email, gender, images;
}
