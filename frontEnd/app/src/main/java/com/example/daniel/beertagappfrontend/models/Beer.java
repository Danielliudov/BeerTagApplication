package com.example.daniel.beertagappfrontend.models;

import java.io.Serializable;

public class Beer implements Serializable {


    private int id;
    private String beername;
    private String brand;
    private String country;
    private String abv;
    private String description;
    private String style;
    private String picture;


    public Beer() {

    }

    public Beer(String beername, String brand, String country, String abv, String description, String style) {
        this.beername = beername;
        this.brand = brand;
        this.country = country;
        this.abv = abv;
        this.description = description;
        this.style = style;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeerName() {
        return beername;
    }

    public void setBeerName(String beername) {
        this.beername = beername;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
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
