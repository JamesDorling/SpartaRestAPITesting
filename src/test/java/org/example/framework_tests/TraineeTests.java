package org.example.framework_tests;

import org.example.DTOs.TraineeDTO;
import org.example.POJOs.Id;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.example.framework.ConnectionManager.*;
import static org.example.framework.Injector.*;

public class TraineeTests {
    TraineeDTO traineeDTO;

    @BeforeEach
    void init() {
        traineeDTO = injectDTOFromFile("src/test/resources/json/IndividualTrainee.json");
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
        @DisplayName("Name is Correct Test")
        void fullNameTest() {Assertions.assertEquals("Keri Valdez", traineeDTO.getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 5), traineeDTO.getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 4), traineeDTO.getEndDateAsDate());
        }
    }

    @Nested
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Correct Test")
        void firstNameTest() {Assertions.assertEquals("Keri", traineeDTO.getFirstName());}

        @Test
        @DisplayName("Last Name is Correct Test")
        void lastNameTest() {Assertions.assertEquals("Valdez", traineeDTO.getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2022-02-05", traineeDTO.getCourseStartDate());}

        @Test
        @DisplayName("Course End Date is Correct")
        void courseEndDateIsCorrect() {Assertions.assertEquals("2022-02-04", traineeDTO.getCourseEndDate());}

        @Test
        @DisplayName("Course ID is Correct")
        void courseIdIsCorrectTest() {Assertions.assertEquals(1, traineeDTO.getCourseId());}

        @Test
        @DisplayName("Trainee ID is an ID")
        void traineeIdIsAnIdTest() {Assertions.assertInstanceOf(Id.class, traineeDTO.getId());}

        @Test
        @DisplayName("Trainee ID is Correct")
        void traineeIdIsCorrectTest() {Assertions.assertEquals(new Id("620132158e281a4c868efd1d").getOid(), traineeDTO.getId().getOid());}

    }
}
