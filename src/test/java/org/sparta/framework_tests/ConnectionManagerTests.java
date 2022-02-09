package org.sparta.framework_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sparta.framework.connection.ConnectionManager;

public class ConnectionManagerTests {
    @Nested
    @DisplayName("Connection Manager URL Tests")
    class UrlTests {
        @Test
        @DisplayName("Blank get connection test")
        void blankGetConnectionTest() {
            Assertions.assertEquals("", ConnectionManager.getConnection());
        }
    }
}
