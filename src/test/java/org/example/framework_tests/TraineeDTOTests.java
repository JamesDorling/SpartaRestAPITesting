package org.example.framework_tests;

import org.example.DTOs.TraineeDTO;
import org.example.framework.ConnectionManager;
import org.junit.jupiter.api.*;

import static org.example.framework.ConnectionManager.*;
import static org.example.framework.Injector.*;

public class TraineeDTOTests {
    TraineeDTO traineeDTO;

    @BeforeEach
    void init() {
        //traineeDTO = injectDTO(getConnection());
    }

    @Nested
    @DisplayName("TraineeDTO Tests")
    class TraineeTests {
        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode());
        }
    }
}
