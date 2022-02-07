package org.example.framework;

import org.example.config.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class ConnectionManager {

    private static final String BASEURL = "https://api.chucknorris.io/jokes/random";
    private static final String TRAINEE_ENDPOINT = "";
    private static final String COURSE_ENDPOINT = "";

    public static String getURL() {
        return BASEURL;
    }

    public static String getConnection() {
        getResponse();
        return getURL();
    }

    public static String getConnectionAndAPIKey() {
        return getURL() + Config.getApiKey();
    }

    public static int getStatusCode() {
        return getResponse().statusCode();
    }

    private static HttpResponse<String> getResponse() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(BASEURL)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }
        return httpResponse;
    }


}
