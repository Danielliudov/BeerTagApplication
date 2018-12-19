package com.example.daniel.beertagappfrontend.views.BeerCreate;

import com.example.daniel.beertagappfrontend.models.Beer;

public interface CreateBeerContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void navigateToHome();

        void showError(Throwable throwable);

        void hideLoading();

        void showLoading();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void save(Beer beer);
    }

    public interface Navigator {

        void navigateToHome();
    }
}
