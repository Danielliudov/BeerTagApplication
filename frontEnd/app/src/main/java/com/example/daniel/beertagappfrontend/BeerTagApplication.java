package com.example.daniel.beertagappfrontend;

import android.app.Application;
import android.content.Context;

import com.example.daniel.beertagappfrontend.async.AsyncSchedulerProvider;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.http.HttpRequester;
import com.example.daniel.beertagappfrontend.http.OkHttpHttpRequester;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.parsers.GsonJsonParser;
import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.example.daniel.beertagappfrontend.repositories.HttpUserRepository;
import com.example.daniel.beertagappfrontend.repositories.base.UserLoginRepository;
import com.example.daniel.beertagappfrontend.services.HttpUsersService;
import com.example.daniel.beertagappfrontend.services.base.UsersService;

import static com.example.daniel.beertagappfrontend.utils.Constants.BASE_SERVER_URL;
import static com.example.daniel.beertagappfrontend.utils.Constants.USER_SERVER_URL;

public class BeerTagApplication extends Application {

    private static SchedulerProvider mSchedulerProvider;
    private static HttpRequester mHttpRequester;
    private static JsonParser<User> mJsonParserUser;
    private static UserLoginRepository<User> mUserRepository;
    private static UsersService mUserService;


    public static SchedulerProvider getSchedulerProvider() {
        if (mSchedulerProvider == null)
            mSchedulerProvider = AsyncSchedulerProvider.getInstance();

        return mSchedulerProvider;
    }

    public static HttpRequester getHttpRequester(Context context) {
        if (mHttpRequester == null)
            mHttpRequester = new OkHttpHttpRequester(context);

        return mHttpRequester;
    }

    public static JsonParser<User> getJsonParserUser() {
        if (mJsonParserUser == null)
            mJsonParserUser = new GsonJsonParser<>(User.class, User[].class);

        return mJsonParserUser;
    }

    public static UserLoginRepository<User> getUsersRepository(Context context) {

        if (mUserRepository == null) {
            String url = BASE_SERVER_URL + USER_SERVER_URL;
            HttpRequester httpRequester = getHttpRequester(context);
            JsonParser<User> jsonParser = getJsonParserUser();

          mUserRepository = new HttpUserRepository<>(
                  httpRequester,
                  url,
                  jsonParser
          );
        }
            return mUserRepository;
    }

    public static UsersService<User> getUsersService(Context context) {
        if (mUserService == null)
            mUserService = new HttpUsersService(context);

        return mUserService;
    }


}


