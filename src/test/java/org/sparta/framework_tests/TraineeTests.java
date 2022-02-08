package org.sparta.framework_tests;

import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.POJOs.Id;
import org.junit.jupiter.api.*;
import org.sparta.POJOs.SpartanEmbedded;
import org.sparta.framework.connection.ConnectionManager;

import java.time.LocalDate;
import java.util.List;

import static org.sparta.framework.connection.ConnectionManager.*;
import static org.sparta.framework.Injector.*;

public class TraineeTests {
    List<TraineeDTO> traineeList;

    @BeforeEach
    void init() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
        traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();
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
        void fullNameTest() {Assertions.assertEquals("Keri Valdez", traineeList.get(0).getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 6, 6), traineeList.get(0).getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 6), traineeList.get(0).getEndDateAsDate());
        }

        @Test
        @DisplayName("First Name is Not Null test")
        void firstNameNotNull() {Assertions.assertTrue(traineeList.get(0).firstNameIsNotNull());}

        @Test
        @DisplayName("Last Name is Not Null test")
        void lastNameNotNull() {Assertions.assertTrue(traineeList.get(0).lastNameIsNotNull());}

        @Test
        @DisplayName("Start Date is Not Null test")
        void startDateNotNull() {Assertions.assertTrue(traineeList.get(0).startDateIsNotNull());}

        @Test
        @DisplayName("end Date is Not Null test")
        void endDateNotNull() {Assertions.assertTrue(traineeList.get(0).endDateIsNotNull());}

        @Test
        @DisplayName("ID is Not Null test")
        void idNotNull() {Assertions.assertTrue(traineeList.get(0).idIsNotNull());}

        @Test
        @DisplayName("Course ID is Not Null test")
        void courseIdNotNull() {Assertions.assertTrue(traineeList.get(0).courseIdIsNotNull());}

        @Test
        @DisplayName("No Data is Null")
        void noDataIsNull() {Assertions.assertTrue(traineeList.get(0).noDataIsNull());}

        @Test
        @DisplayName("Start Date is before End Date")
        void startIsBeforeEnd() {
            Assertions.assertTrue(traineeList.get(0).startIsBeforeEnd());
        }

        @Test
        @DisplayName("End Date is after Start Date") //Redundant test, but if one fails and one passes something is up
        void endIsAfterStart() {
            Assertions.assertTrue(traineeList.get(0).endIsAfterStart());
        }

        @Test
        @DisplayName("Start Date is after 0")
        void startIsAfterTheCalendarStarted() {Assertions.assertTrue(traineeList.get(0).startIsAfter(LocalDate.of(2022, 1, 1)));}

        @Test
        @DisplayName("End Date is before the set max date")
        void endIsBeforeTheEndOfTime() {Assertions.assertTrue(traineeList.get(0).endIsBefore(LocalDate.of(2050, 12, 31)));}

        @Test
        @DisplayName("Course Name is correct")
        void courseNameIsCorrect() {Assertions.assertEquals("java", traineeList.get(0).getCourseName());}
    }

    @Nested
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Correct Test")
        void firstNameTest() {Assertions.assertEquals("Keri", traineeList.get(0).getFirstName());}

        @Test
        @DisplayName("Last Name is Correct Test")
        void lastNameTest() {Assertions.assertEquals("Valdez", traineeList.get(0).getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2022-06-06", traineeList.get(0).getCourseStartDate());}

        @Test
        @DisplayName("Course End Date is Correct")
        void courseEndDateIsCorrect() {Assertions.assertEquals("2022-02-06", traineeList.get(0).getCourseEndDate());}

        @Test
        @DisplayName("Course ID is Correct")
        void courseIdIsCorrectTest() {Assertions.assertEquals(1, traineeList.get(0).getCourseId());}

        @Test
        @DisplayName("Trainee ID is Correct")
        void traineeIdIsCorrectTest() {Assertions.assertEquals("620132158e281a4c868efd1d", traineeList.get(0).getId());}
    }

    @Nested
    @DisplayName("Crud Operation Tests")
    class CrudOperationTests {
        @Test
        @DisplayName("Posting a trainee")
        void postingATraineeTest() {
            System.out.println(traineeList.get(0).getTraineeAsJson());
            TraineeDTO newTrainee = new TraineeDTO("james", "dorling", 1, "2022-01-01");
            ConnectionManager.sendTraineePostRequest(newTrainee.getTraineeAsJson(),
                    ConnectionManager.makeUrl().spartan().link());

        }
    }

}
