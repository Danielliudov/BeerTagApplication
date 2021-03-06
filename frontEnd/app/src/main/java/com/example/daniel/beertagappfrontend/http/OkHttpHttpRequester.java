package com.example.daniel.beertagappfrontend.http;

import android.content.Context;

import com.example.daniel.beertagappfrontend.BeerTagApplication;
import com.example.daniel.beertagappfrontend.utils.Constants;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;


import static com.example.daniel.beertagappfrontend.utils.Constants.BASE_SERVER_URL;
import static com.example.daniel.beertagappfrontend.utils.Constants.MYUSERNAME_SERVER_URL;
import static com.example.daniel.beertagappfrontend.utils.Constants.USER_SERVER_URL;

public class OkHttpHttpRequester implements HttpRequester {

    private ClearableCookieJar mCookieJar;

    public OkHttpHttpRequester(Context context) {
        mCookieJar = BeerTagApplication.getCookieJar(context);
    }

    @Override
    public String get(String url) throws IOException {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(mCookieJar)
                .addInterceptor(interceptor)
                .build();

        Response response = client.newCall(request)
                .execute();

        assert response.body() != null;
        return response.body().string();
    }

    @Override
    public String post(String url, String body) throws IOException {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=UTF-8"),
                body
        );

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(mCookieJar)
                .addInterceptor(interceptor)
                .build();

        Response response = client.newCall(request)
                .execute();

        assert response.body() != null;
        return response.body().string();

    }

    @Override
    public void put(String url, String body) throws IOException {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                body
        );

        Request request = new Request.Builder()
                .put(requestBody)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(mCookieJar)
                .addInterceptor(interceptor)
                .build();

        Response response = client.newCall(request)
                .execute();

    }

    @Override
    public String login(String username, String password) throws IOException {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Request request = new Request.Builder()
                .addHeader("username", username)
                .addHeader("password", password)
                .get()
                .url(Constants.BASE_SERVER_URL + "/users/me")
                .build();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cookieJar(mCookieJar)
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) {
                        if (responseCount(response) >= 3) {
                            return null;
                        }
                        String credential = Credentials.basic(username, password);
                        return response.request().newBuilder().header("Authorization", credential).build();
                    }
                });
        OkHttpClient client = clientBuilder.build();

        Response response = client.newCall(request)
                .execute();

        assert response.body() != null;
        return response.body().string();
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
