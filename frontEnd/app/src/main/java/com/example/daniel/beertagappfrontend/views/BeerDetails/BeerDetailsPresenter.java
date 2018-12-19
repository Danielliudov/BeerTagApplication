package com.example.daniel.beertagappfrontend.views.BeerDetails;

import android.content.Context;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.services.base.BeerService;

public class BeerDetailsPresenter implements BeerDetailsContracts.Presenter {
    private final BeerService<Beer> mBeerService;
    private final SchedulerProvider mSchedulerProvider;
    private  BeerDetailsContracts.View mView;
    private int mBeerId;

    public BeerDetailsPresenter(Context context) {
        mBeerService = BeerTagApplication.getmBeerService(context);
        mSchedulerProvider = BeerTagApplication.getSchedulerProvider();
    }

    @Override
    public void subscribe(BeerDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadBeer() {

    }

    @Override
    public void setBeerId(int id) {

    }
}
