package com.example.daniel.beertagappfrontend.http;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import static com.example.daniel.beertagappfrontend.constants.Constants.BASE_SERVER_URL;
import static com.example.daniel.beertagappfrontend.constants.Constants.MYUSERNAME_SERVER_URL;
import static com.example.daniel.beertagappfrontend.constants.Constants.USER_SERVER_URL;

public class OkHttpHttpRequester implements HttpRequester {

    public OkHttpHttpRequester() {
    }

    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request)
                .execute();
        String body = response.body().string();
        return body;
    }

    @Override
    public String post(String url, String bodyString) throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                bodyString

        );

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();


        Response response = client.newCall(request)
                .execute();

      String responseBody = response.body().string();
      return responseBody;
    }

    @Override
    public String login(String username, String password) throws IOException {

            String url = BASE_SERVER_URL + USER_SERVER_URL + MYUSERNAME_SERVER_URL;

        return new OkHttpClient.Builder()
                .authenticator((route, response) ->
                {
                    if (responseCount(response) >= 3)
                        return null;

                    String credentials = Credentials.basic(username, password);

                    return response.request()
                            .newBuilder()
                            .header("Authorization", credentials)
                            .build();
                })
                .build()
                .newCall(new Request.Builder()
                        .addHeader("username", username)
                        .addHeader("password", password)
                        .get()
                        .url(url)
                        .build())
                .execute()
                .body()
                .string();

    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null)
            ++result;

        return result;
    }
}
