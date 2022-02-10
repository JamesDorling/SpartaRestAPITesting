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

    public static HttpResponse<String> sendTraineePostRequest(String newTraineeJson, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers
                .ofString(newTraineeJson)).header("Content-Type", "application/json").build();
        return sendRequest(httpRequest);
    }

    public static HttpResponse<String> sendTraineePutRequest(String newTraineeJson, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).PUT(HttpRequest.BodyPublishers
                .ofString(newTraineeJson)).header("Content-Type", "application/json").build();
        return sendRequest(httpRequest);
    }

    public static HttpResponse<String> sendCoursePostRequest(String newCourseJson, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers
                .ofString(newCourseJson)).header("Content-Type", "application/json").build();
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
}
