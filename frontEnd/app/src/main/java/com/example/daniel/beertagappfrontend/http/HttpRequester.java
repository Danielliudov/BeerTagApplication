package com.example.daniel.beertagappfrontend.http;

import java.io.IOException;

public interface HttpRequester {

    String get(String url) throws IOException;

    String post(String url, String BodyString) throws IOException;

    String login (String username, String password) throws IOException;

}
