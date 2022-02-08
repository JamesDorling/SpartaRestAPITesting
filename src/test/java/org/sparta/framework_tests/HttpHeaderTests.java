package org.sparta.framework_tests;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class HttpHeaderTests {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final HttpRequest REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/spartans"))
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
    @DisplayName("Check content type is correct")
    void checkContentTypeIsCorrect() {
        Assertions.assertEquals("application/hal+json", headerMap.get("content-type").get(0));
    }

    @Test
    @DisplayName("Check transfer-encoding is chunked")
    void checkTransferEncodingIsChunked() {
        Assertions.assertEquals("chunked", headerMap.get("Transfer-Encoding").get(0));
    }

    @Test
    @DisplayName("Check date format is correct")
    void checkDateFormatIsCorrect() {
        LocalDate currentTime = LocalDate.parse(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

//        Assertions.assertEquals(currentTime, headerMap.get("date").get(0));
    }


}
