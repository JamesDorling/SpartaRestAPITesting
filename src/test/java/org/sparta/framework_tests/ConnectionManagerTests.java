package org.sparta.framework_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sparta.framework.connection.ConnectionManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConnectionManagerTests {
    @Nested
    @DisplayName("Connection Manager URL Tests")
    class UrlTests {
        @Test
        @DisplayName("getConnection test")
        void getConnectionTest() {
            Assertions.assertEquals("http://localhost:8080", ConnectionManager.getConnection());
        }

        @Test
        @DisplayName(("getURL test"))
        void getUrlTest() {
            Assertions.assertEquals("http://localhost:8080", ConnectionManager.getURL());
        }
    }

    @Nested
    @DisplayName("makeUrl Tests")
    class MakeUrlTests {
        @Test
        @DisplayName("link test")
        void linkTest() {
            Assertions.assertEquals("http://localhost:8080", ConnectionManager.makeUrl().link());
        }

        @Test
        @DisplayName("spartan test")
        void spartanTest() {
            String exampleLink = ConnectionManager.makeUrl().spartan().firstName("Hendrix").lastName("Gardner").link();
            Assertions.assertTrue( exampleLink.contains("http://localhost:8080/spartans?") && exampleLink.contains("firstName=Hendrix&") && exampleLink.contains("lastName=Gardner&"));
        }

        @Test
        @DisplayName("course test")
        void courseTest() {
           Assertions.assertEquals("http://localhost:8080/courses?" , ConnectionManager.makeUrl().course().link());
        }

        @Test
        @DisplayName("date test")
        void dateTest() {
            Assertions.assertEquals("http://localhost:8080/spartans?date=2022-02-10&BeforeAfter=BeforeStartEnd=End" , ConnectionManager.makeUrl().spartan().date(LocalDate.of(2022, 2, 10).toString()).BeforeAfter(ConnectionManager.UrlBuilder.TimeParameters.BEFORE).StartEnd(ConnectionManager.UrlBuilder.TimeParameters.END).link());
        }

        @Test
        @DisplayName("courseName test")
        void courseNameTest() {
            Assertions.assertEquals("http://localhost:8080/spartans?courseName=Java&" , ConnectionManager.makeUrl().spartan().courseName("Java").link());
        }

        @Test
        @DisplayName("courseId test")
        void courseIdTest() {
            String exampleLink = ConnectionManager.makeUrl().spartan().courseId(3).link();
            Assertions.assertEquals("http://localhost:8080/spartans?courseId=3&", exampleLink);
        }

        @Test
        @DisplayName("specific spartan test")
        void specificSpartanTest() {
            Assertions.assertEquals("http://localhost:8080/spartans/6203a427147baa0d50338b6f" , ConnectionManager.makeUrl().getSpecificSpartan("6203a427147baa0d50338b6f"));
        }

        @Test
        @DisplayName("specific course test")
        void specificCourseTest() {
            Assertions.assertEquals("http://localhost:8080/courses/3" , ConnectionManager.makeUrl().getSpecificCourse(3));
        }

        @Test
        @DisplayName("apikey test")
        void apiKeyTest() {
            Assertions.assertEquals("http://localhost:8080key", ConnectionManager.makeUrl().apiKey().link());
        }
    }
}
