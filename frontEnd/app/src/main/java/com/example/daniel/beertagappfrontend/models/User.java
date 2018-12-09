package com.example.daniel.beertagappfrontend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private Set<Role> role;
    private Set<Beer> beerHasDrank;


    public User() {

    }


    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return new HashSet<>(role);
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Beer> getBeerHasDrank() {
        return new HashSet<>(beerHasDrank);
    }

    public void setBeerHasDrank(Set<Beer> beerHasDrank) {
        this.beerHasDrank = beerHasDrank;
    }
}
