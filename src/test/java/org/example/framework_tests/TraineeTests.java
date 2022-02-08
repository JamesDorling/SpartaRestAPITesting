package org.example.framework_tests;

import org.example.DTOs.DTO;
import org.example.DTOs.DTOEnum;
import org.example.DTOs.TraineeDTO;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.example.framework.ConnectionManager.*;
import static org.example.framework.Injector.*;

public class TraineeTests {
    TraineeDTO traineeDTO;

    @BeforeEach
    void init() {
        traineeDTO = (TraineeDTO) injectDTOFromFile("src/test/resources/json/traineeManish.json", DTOEnum.TRAINEE);
//        traineeDTO = injectDTOFromFile("src/test/resources/json/traineeManish.json");
    }

    @Nested
    @DisplayName("TraineeDTO Tests")
    class TraineeDTOTests {
        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode());
        }

        @Test
        @DisplayName("Name is Manish The-Beast Test")
        void fullNameTest() {Assertions.assertEquals("manish the-beast", traineeDTO.getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2010, 1, 1), traineeDTO.getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2010, 12, 12), traineeDTO.getEndDateAsDate());
        }
    }

    @Nested
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Manish Test")
        void firstNameTest() {Assertions.assertEquals("manish", traineeDTO.getFirstName());}

        @Test
        @DisplayName("Last Name is the-beast Test")
        void lastNameTest() {Assertions.assertEquals("the-beast", traineeDTO.getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2010-01-01", traineeDTO.getCourseStartDate());}

    }
}
