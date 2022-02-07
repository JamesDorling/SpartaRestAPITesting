package org.example.framework_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class HttpHeaderTests {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final HttpRequest REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("https://api.chucknorris.io/jokes/random"))
            .build();
    private static Map<String, List<String>> headerMap;

    @BeforeAll
    static void init() {
        try {
            HttpResponse<String> response = CLIENT.send(REQUEST, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers = response.headers();
            headerMap = headers.map();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Print map of headers")
    void print() {
        System.out.println(headerMap);
    }

    @Test
    @DisplayName("Check status code is 200")
    void checkStatusCodeIs200() {
        Assertions.assertEquals(headerMap.get(":status").get(0), "200");
    }

    @Test
    @DisplayName("Check server is cloudflare")
    void checkServerIsCloudflare() {
        Assertions.assertEquals(headerMap.get("server").get(0), "cloudflare");
    }


}
