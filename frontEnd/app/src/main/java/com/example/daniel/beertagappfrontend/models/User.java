package com.example.daniel.beertagappfrontend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<Role> role;


    public User() {

    }


    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
