package com.example.daniel.beertagappfrontend.views.home;

import android.content.Context;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.services.base.BeerService;
import com.example.daniel.beertagappfrontend.views.BeerCreate.CreateBeerContracts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class HomePagePresenter implements HomePageContracs.Presenter {

    private final SchedulerProvider mSchedulerProvider;
    private final BeerService mBeerService;
    private HomePageContracs.View mView;

    HomePagePresenter(Context context) {
        mSchedulerProvider = BeerTagApplication.getSchedulerProvider();
        mBeerService = BeerTagApplication.getmBeerService(context);
    }

    @Override
    public void subscribe(HomePageContracs.View view) {
            mView = view;
    }

    @Override
    public void loadBeer() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Beer>>) emitter -> {
                    List<Beer> superheroes = mBeerService.getAll();
                    emitter.onNext(superheroes);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(
                        this::presentBeersToView,
                        error -> mView.showError(error)
                );
    }

    @Override
    public void filterBeers(String pattern) {

    }

    @Override
    public void selectBeer(Beer beer) {
        mView.showBeerDetails(beer);
    }


    private void presentBeersToView(List<Beer> beers) {
        if (beers.isEmpty()) {
            mView.showEmptyBeerList();
        } else {
            mView.showBeers(beers);
        }
    }
}
