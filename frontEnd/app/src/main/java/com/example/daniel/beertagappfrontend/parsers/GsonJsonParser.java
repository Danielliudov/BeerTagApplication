package com.example.daniel.beertagappfrontend.parsers;

import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {


    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;

    public GsonJsonParser(Class<T> mKlass, Class<T[]> mArrayKlass) {
        this.mKlass = mKlass;
        this.mArrayKlass = mArrayKlass;
        this.mGson = new Gson();
    }

    @Override
    public List<T> fromJsonArray(String jsonString) {
        T[] result = mGson.fromJson(jsonString, mArrayKlass);
        return Arrays.asList(result);
    }

    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mKlass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }
}
