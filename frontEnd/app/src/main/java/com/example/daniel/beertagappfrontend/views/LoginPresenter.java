package com.example.daniel.beertagappfrontend.views;


import android.content.Context;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.services.base.UsersService;
import com.example.daniel.beertagappfrontend.utils.enums.ErrorCode;
import com.example.daniel.beertagappfrontend.validators.RegisterValidator;
import com.example.daniel.beertagappfrontend.validators.base.LoginValidator;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContracts.Presenter {


    private final UsersService<User> mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;
    private final LoginValidator mLoginValidator;


    public LoginPresenter(Context context){
        mUsersService = BeerTagApplication.getUsersService(context);
        mSchedulerProvider = BeerTagApplication.getSchedulerProvider();
        mLoginValidator = new RegisterValidator();
    }



    @Override
    public void register(User user) {

    }

    @Override
    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(mView.getActivity(), "username and password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        } else {
            mView.showLoading();
            Disposable disposable = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User currentUser = mUsersService.login(username, password);
                        if (currentUser == null) throw new Exception("Wrong credentials.");
                        emitter.onNext(currentUser);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doOnEach(x -> mView.hideLoading())
                    .doOnError(e -> mView.showError(e))
                    .subscribe(s -> mView.navigateToHome(s));
        }
    }
    @Override
    public void subscribe(LoginContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public Set<ErrorCode> checkCredentials(String username, String pass, String passConf) {
        Set<ErrorCode> errorCodes = new HashSet<>();
        errorCodes.add(mLoginValidator.isUsernameValid(username));
        errorCodes.add(mLoginValidator.isPasswordValid(pass, passConf));
        return errorCodes;
    }
}
