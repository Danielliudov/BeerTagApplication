package com.example.daniel.beertagappfrontend.repositories.base;


import java.io.IOException;
import java.util.List;


public interface UserLoginRepository<T> {

    List<T> getAll() throws Exception;

    T getById(int id) throws IOException;

    T add(T item) throws Exception;

    T login(String username, String password) throws IOException;
}
