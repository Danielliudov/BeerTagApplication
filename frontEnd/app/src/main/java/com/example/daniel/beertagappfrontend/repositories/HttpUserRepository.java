package com.example.daniel.beertagappfrontend.repositories;

import com.example.daniel.beertagappfrontend.http.HttpRequester;
import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.example.daniel.beertagappfrontend.repositories.base.UserLoginRepository;

import java.io.IOException;
import java.util.List;

public class HttpUserRepository<T> implements UserLoginRepository<T> {

    private final HttpRequester mHttpRequester;

    private final String mUserEndPoint;

    private final JsonParser<T> mUsersJsonParser;

    public HttpUserRepository(HttpRequester mHttpRequester, String mUserEndPoint, JsonParser<T> mUsersJsonParser) {
        this.mHttpRequester = mHttpRequester;
        this.mUserEndPoint = mUserEndPoint;
        this.mUsersJsonParser = mUsersJsonParser;
    }


    @Override
    public List<T> getAll() throws Exception {

        final String url = mUserEndPoint + "/secured/all";

        String json = mHttpRequester.get(url);

        return mUsersJsonParser.fromJsonArray(json);

    }

    @Override
    public T getById(int id) throws IOException {
        final String url = mUserEndPoint + "/" + id;

        String json = mHttpRequester.get(url);

        return mUsersJsonParser.fromJson(json);
    }

    @Override
    public T add(T item) throws Exception {

        final String url = mUserEndPoint + "/signup";
        String requestBody = mUsersJsonParser.toJson(item);
        String respondBody = mHttpRequester.post(url, requestBody);

        return mUsersJsonParser.fromJson(respondBody);

    }

    @Override
    public T login(String username, String password) throws IOException {

        String responseBody = mHttpRequester.login(username, password);

        return mUsersJsonParser.fromJson(responseBody);
    }
}
