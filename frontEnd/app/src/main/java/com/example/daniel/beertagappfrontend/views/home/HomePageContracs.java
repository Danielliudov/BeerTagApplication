package com.example.daniel.beertagappfrontend.views.home;

import com.example.daniel.beertagappfrontend.models.Beer;

import java.util.List;

public interface HomePageContracs {

    interface View {
        void setPresenter(Presenter presenter);

        void showBeers(List<Beer> beers);

        void showEmptyBeerList();

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void showBeerDetails(Beer beer);
    }
    interface Presenter {
        void subscribe(View view);

        void loadBeer();

        void filterBeers(String pattern);

        void selectBeer(Beer beer);
    }

    interface Navigator {
        void navigateWith(Beer beer);
    }
}
