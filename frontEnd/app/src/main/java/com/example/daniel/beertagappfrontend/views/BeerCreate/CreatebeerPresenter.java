package com.example.daniel.beertagappfrontend.views.BeerCreate;

import android.content.Context;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.services.base.BeerService;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class CreatebeerPresenter implements CreateBeerContracts.Presenter {
    private final BeerService<Beer> mBeerService;
    private final SchedulerProvider mSchedulerProvider;
    private CreateBeerContracts.View mView;

    public CreatebeerPresenter(Context context) {
        mBeerService = BeerTagApplication.getmBeerService(context);
        mSchedulerProvider = BeerTagApplication.getSchedulerProvider();
    }


    @Override
    public void subscribe(CreateBeerContracts.View view) {
     mView = view;
    }

    @Override
    public void unsubscribe() {
    mView = null;
    }

    @Override
    public void save(Beer beer) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Beer>) emitter -> {
                    Beer createdBeer = mBeerService.add(beer);
                    emitter.onNext(createdBeer);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .doOnError(mView::showError)
                .subscribe(s -> mView.navigateToHome());
    }
}
