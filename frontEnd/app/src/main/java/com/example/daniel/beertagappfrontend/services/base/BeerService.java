package com.example.daniel.beertagappfrontend.services.base;

import java.io.IOException;
import java.util.List;

public interface BeerService<T> {

    List<T> getAll() throws Exception;

    T add(T item) throws Exception;

    T getByCountry(String country) throws IOException;
}
