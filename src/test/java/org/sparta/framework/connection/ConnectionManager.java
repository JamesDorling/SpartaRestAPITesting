package org.sparta.framework.connection;

import org.sparta.config.Config;
import org.sparta.framework.logging.LogManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;


public class ConnectionManager {

    private static final String BASEURL = "http://localhost:8080";

    public static String getURL() {
        return BASEURL;
    }

    public static String getConnection() {
        getResponse();
        return getURL();
    }

    public static UrlBuilder makeUrl() {
        return new UrlBuilder(BASEURL);
    }

    public static int getStatusCode() {
        return getResponse().statusCode();
    }

    public static int getStatusCode(String url) {
        return getResponse(url).statusCode();
    }

    private static HttpResponse<String> getResponse(String url) {
        return receiveResponse(url);
    }

    private static HttpResponse<String> getResponse() {
        return receiveResponse(BASEURL + "/spartans");
    }

    private static HttpResponse<String> receiveResponse(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            LogManager.writeLog(Level.INFO, "Connected to server, http response is: " + httpResponse.toString());
        } catch (IOException | InterruptedException e) {
            LogManager.writeLog(Level.SEVERE, "Error sending HTTP request");
        }
        return httpResponse;
    }

    public static HttpResponse<String> sendPostRequest(String newJson, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers
                .ofString(newJson)).header("Content-Type", "application/json").build();
        return sendRequest(httpRequest);
    }

    public static HttpResponse<String> sendPutRequest(String newJson, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).PUT(HttpRequest.BodyPublishers
                .ofString(newJson)).header("Content-Type", "application/json").build();
        return sendRequest(httpRequest);
    }

    public static HttpResponse<String> sendDeleteRequest(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();
        return sendRequest(httpRequest);
    }


    private static HttpResponse<String> sendRequest(HttpRequest request) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            LogManager.writeLog(Level.INFO, "Connected to server, http response is: " + httpResponse.toString());
        } catch (IOException | InterruptedException e) {
            LogManager.writeLog(Level.SEVERE, "Error sending HTTP request");
        }
        return httpResponse;
    }

    public static String getAdminKey(){
        String username = Config.getUsername();
        String password = Config.getPassword();
        String getAdminURL = ConnectionManager.makeUrl().getAdminKey().userNameAndPassword(username, password).link();
        System.out.println(getAdminURL);
        String result = extractKey(getResponse(getAdminURL).body());
        System.out.println(result);
        return result;
    }

    public static String extractKey(String string) {
        String str = string;
        str = str.replace("Your API key is:", "");

        return str.trim();
    }
}
