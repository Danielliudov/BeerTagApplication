package com.example.daniel.beertagappfrontend;

import android.app.Application;
import android.content.Context;

import com.example.daniel.beertagappfrontend.async.AsyncSchedulerProvider;
import com.example.daniel.beertagappfrontend.async.base.SchedulerProvider;
import com.example.daniel.beertagappfrontend.http.HttpRequester;
import com.example.daniel.beertagappfrontend.http.OkHttpHttpRequester;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.parsers.GsonJsonParser;
import com.example.daniel.beertagappfrontend.parsers.base.JsonParser;
import com.example.daniel.beertagappfrontend.repositories.HttpBeerRepository;
import com.example.daniel.beertagappfrontend.repositories.HttpUserRepository;
import com.example.daniel.beertagappfrontend.repositories.base.BeerRepository;
import com.example.daniel.beertagappfrontend.repositories.base.UserLoginRepository;
import com.example.daniel.beertagappfrontend.services.HttpBeerService;
import com.example.daniel.beertagappfrontend.services.HttpUsersService;
import com.example.daniel.beertagappfrontend.services.base.BeerService;
import com.example.daniel.beertagappfrontend.services.base.UsersService;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import static com.example.daniel.beertagappfrontend.utils.Constants.BASE_SERVER_URL;
import static com.example.daniel.beertagappfrontend.utils.Constants.BEER_SERVER_URL;
import static com.example.daniel.beertagappfrontend.utils.Constants.USER_SERVER_URL;

public class BeerTagApplication extends Application {

    private static SchedulerProvider mSchedulerProvider;
    private static HttpRequester mHttpRequester;
    private static JsonParser<User> mJsonParserUser;
    private static JsonParser<Beer> mJsonParserBeer;
    private static UserLoginRepository<User> mUserRepository;
    private static BeerRepository<Beer> mBeerRepository;
    private static UsersService<User> mUserService;
    private static BeerService<Beer> mBeerService;
    private static ClearableCookieJar mCookieJar;

    @Override
    public void onCreate() {
        super.onCreate();
    }

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

    public static JsonParser<Beer> gerJsonParserBeer() {
        if (mJsonParserBeer == null) {

            mJsonParserBeer = new GsonJsonParser<>(Beer.class, Beer[].class);
        }

        return mJsonParserBeer;
    }


    public static UserLoginRepository<User> getUsersRepository(Context context) {

        if (mUserRepository == null) {
            String url = BASE_SERVER_URL + "/users";
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

    public static BeerRepository<Beer> getmBeerRepository(Context context) {

        if (mBeerRepository == null) {
            String url = BASE_SERVER_URL + "/beers";
            HttpRequester httpRequester = getHttpRequester(context);
            JsonParser<Beer> jsonParser = gerJsonParserBeer();

            mBeerRepository = new HttpBeerRepository<>(
                    httpRequester,
                    url,
                    jsonParser
            );
        }

        return mBeerRepository;
    }

    public static UsersService<User> getUsersService(Context context) {
        if (mUserService == null)
            mUserService = new HttpUsersService(context);

        return mUserService;
    }

    public static BeerService<Beer> getmBeerService(Context context) {
        if (mBeerService == null)
            mBeerService = new HttpBeerService(context);

        return mBeerService;

    }

    public static ClearableCookieJar getCookieJar(Context ctx) {
        if (mCookieJar == null)
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(ctx));
        return mCookieJar;
    }


}


