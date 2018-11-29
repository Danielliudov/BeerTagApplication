package com.example.daniel.beertagappfrontend.parsers;

import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser {


    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;

    public GsonJsonParser(Class<T> mKlass, Class<T[]> mArrayKlass, Gson mGson) {
        this.mKlass = mKlass;
        this.mArrayKlass = mArrayKlass;
        this.mGson = mGson;
    }


    @Override
    public List fromJsonArray(String jsonString) {
        T[] result = mGson.fromJson(jsonString, mArrayKlass);
        return Arrays.asList(result);
    }

    @Override
    public Object fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mKlass);
    }

    @Override
    public String toJson(Object object) {
        return mGson.toJson(object);
    }
}
