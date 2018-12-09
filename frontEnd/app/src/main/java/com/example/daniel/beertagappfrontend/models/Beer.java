package com.example.daniel.beertagappfrontend.models;

import java.io.Serializable;

public class Beer implements Serializable {


    private int id;
    private String name;
    private String brandname;
    private String country;
    private int abv;
    private String description;
    private String style;
    private String picture;


    public Beer() {

    }

    public Beer(String name, String brandname, String country, int abv, String description, String style, String picture) {
        this.name = name;
        this.brandname = brandname;
        this.country = country;
        this.abv = abv;
        this.description = description;
        this.style = style;
        this.picture = picture;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAbv() {
        return abv;
    }

    public void setAbv(int abv) {
        this.abv = abv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
