package com.example.daniel.beertagappfrontend.views.BeerDetails;

import com.example.daniel.beertagappfrontend.models.Beer;

public interface BeerDetailsContracts {

    interface View {
        void showBeer(Beer beer);

        void setPresenter(Presenter presenter);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void subscribe(View view);

        void loadBeer();

        void setBeerId(int id);
    }
}
