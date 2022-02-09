package org.sparta.framework.connection;

import org.sparta.DTOs.TraineeDTO;
import org.sparta.config.Config;
import org.sparta.framework.logging.LogManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static class UrlBuilder {
        private final StringBuilder link;

        public UrlBuilder(String link) {this.link = new StringBuilder(link);}

        public UrlBuilder spartan() {
            link.append("/spartans?");
            return this;
        }

        public UrlBuilder course() {
            link.append("/courses?");
            return this;
        }

        public UrlBuilder firstName(String name) {
            link.append("firstName=").append(name).append("&");
            return this;
        }

        public UrlBuilder lastName(String name) {
            link.append("lastName=").append(name).append("&");
            return this;
        }

        public UrlBuilder courseId(Integer id) {
            link.append("courseId=").append(id).append("&");
            return this;
        }

        public UrlBuilder date(String date) {
            link.append("date=").append(date).append("&");
            return this;
        }

        public UrlBuilder courseName(String course) {
            link.append("name=").append(course).append("&");
            return this;
        }

        public UrlBuilder length(String length) {
            link.append("length=").append(length).append("&");
            return this;
        }

        public UrlBuilder BeforeAfter(TimeParameters parameter) {
            link.append("BeforeAfter=");
            switch (parameter) {
                case BEFORE -> link.append("Before");
                case AFTER -> link.append("After");
                case NOW -> link.append("Now");
                default -> throw new IllegalStateException("Unexpected value (Time Parameter): " + parameter.name());
            }
            return this;
        }

        public UrlBuilder StartEnd(TimeParameters parameter) {
            link.append("StartEnd=");
            switch (parameter) {
                case START -> link.append("Start");
                case END -> link.append("End");
                default -> throw new IllegalStateException("Unexpected value (Time Parameter): " + parameter.name());
            }
            return this;
        }

        public String getSpartanWithKey() {
            return link.append("/spartans/").append(Config.getApiKey()).toString();
        }

        public String getCourseWithKey() {
            return link.append("/courses/").append(Config.getApiKey()).toString();
        }

        public String getSpecificSpartan(String id) {
            return link.append("/spartans/").append(id).toString();
        }

        public String getSpecificCourse(Integer id) {
            return link.append("/courses/").append(id).toString();
        }

        public String getAllActiveCourses() {
            return link.append("/courses/isActive").toString();
        }

        public String getAllInactiveCourses() {
            return link.append("/courses/nonActive").toString();
        }

        public String link() { //Trailing & is fine, still works
            return link.toString();
        }

        public enum TimeParameters {
            BEFORE,
            NOW,
            AFTER,
            START,
            END
        }
    }
}
