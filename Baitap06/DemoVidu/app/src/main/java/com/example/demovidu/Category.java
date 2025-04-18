package com.example.demovidu;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    private String images;
    private String description;
}
