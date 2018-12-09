package com.example.daniel.beertagappfrontend.services;

import android.content.Context;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.repositories.base.BeerRepository;
import com.example.daniel.beertagappfrontend.services.base.BeerService;

import java.io.IOException;
import java.util.List;

public class HttpBeerService implements BeerService<Beer> {

    public final BeerRepository<Beer> beerRepository;


    public HttpBeerService(Context context) {
        beerRepository = BeerTagApplication.getmBeerRepository(context);
    }

    @Override
    public List<Beer> getAll() throws Exception {
        return beerRepository.getAll();
    }

    @Override
    public Beer add(Beer item) throws Exception {
        return beerRepository.add(item);
    }

    @Override
    public Beer getByCountry(String country) throws IOException {
        return beerRepository.getByCountry(country);
    }
}
