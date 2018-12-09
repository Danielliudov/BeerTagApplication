package com.example.daniel.beertagappfrontend.views;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;

import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.enums.ErrorCode;

import java.util.Map;
import java.util.Set;

public interface LoginContracts {


    interface Presenter {
        void register(User user);

        void login(String email, String password);

        void subscribe(View view);

        void unsubscribe();

        Set<ErrorCode> checkCredentials(String s, String toString, String string);
    }


    interface View {
        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        Activity getActivity();

        void setRegisterErrors(Set<ErrorCode> errors, Map<String, TextInputLayout> tils);

        void showError(Throwable throwable);

        void navigateToHome(User user);
    }
}
