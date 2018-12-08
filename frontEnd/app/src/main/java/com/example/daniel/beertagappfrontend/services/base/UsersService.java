package com.example.daniel.beertagappfrontend.services.base;

import com.example.daniel.beertagappfrontend.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersService<T> {

    List<T> getAll() throws Exception;

    T create(T user) throws Exception;

    T login(String username, String password) throws IOException;

}
