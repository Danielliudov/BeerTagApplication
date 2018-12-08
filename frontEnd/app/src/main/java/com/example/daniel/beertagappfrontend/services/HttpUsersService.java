package com.example.daniel.beertagappfrontend.services;

import android.content.Context;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.repositories.base.UserLoginRepository;
import com.example.daniel.beertagappfrontend.services.base.UsersService;

import java.io.IOException;
import java.util.List;

public class HttpUsersService implements UsersService<User> {

    public final UserLoginRepository<User> userUserLoginRepository;

    public HttpUsersService(Context context) {
        userUserLoginRepository = BeerTagApplication.getUsersRepository(context);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userUserLoginRepository.getAll();
    }

    @Override
    public User create(User user) throws Exception {
        return userUserLoginRepository.add(user);
    }

    @Override
    public User login(String username, String password) throws IOException {
        return userUserLoginRepository.login(username,password);
    }
}
