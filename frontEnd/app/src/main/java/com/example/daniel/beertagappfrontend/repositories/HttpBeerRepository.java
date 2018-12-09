package com.example.daniel.beertagappfrontend.repositories;

import com.example.daniel.beertagappfrontend.http.HttpRequester;
import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.example.daniel.beertagappfrontend.repositories.base.BeerRepository;

import java.io.IOException;
import java.util.List;

public class HttpBeerRepository<T> implements BeerRepository<T> {

    private final HttpRequester mHttpRequester;

    private final String mBeerEndPoint;

    private final JsonParser<T> mBeerJsonParser;

    public HttpBeerRepository(HttpRequester mHttpRequester, String mBeerEndPoint, JsonParser<T> mBeerJasonParser) {
        this.mHttpRequester = mHttpRequester;
        this.mBeerEndPoint = mBeerEndPoint;
        this.mBeerJsonParser = mBeerJasonParser;
    }

    @Override
    public List<T> getAll() throws Exception {
        final String url = mBeerEndPoint + "/getAll";

        String json = mHttpRequester.get(url);

        return mBeerJsonParser.fromJsonArray(json);
    }


    @Override
    public T add(T item) throws Exception {
        final String url = mBeerEndPoint + "/addBeer";
        String requestBody = mBeerJsonParser.toJson(item);
        String respondBody = mHttpRequester.post(url, requestBody);

        return mBeerJsonParser.fromJson(respondBody);
    }

    @Override
    public T getByCountry(String country) throws IOException {
        final String url = mBeerEndPoint + "/get/" + country;

        String json = mHttpRequester.get(url);

        return mBeerJsonParser.fromJson(json);
    }

    // "/get/{beerCountry}","/addBeer"


}
