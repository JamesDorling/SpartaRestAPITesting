package org.sparta.framework_tests;

import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.POJOs.Id;
import org.junit.jupiter.api.*;
import org.sparta.POJOs.SpartanEmbedded;
import org.sparta.crud_forms.TraineeForm;
import org.sparta.framework.connection.ConnectionManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        void fullNameTest() {Assertions.assertEquals("Macias Monroe", traineeList.get(0).getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 8), traineeList.get(0).getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 3, 15), traineeList.get(0).getEndDateAsDate());
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
        void courseNameIsCorrect() {Assertions.assertEquals("business", traineeList.get(0).getCourseName());}
    }

    @Nested
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Correct Test")
        void firstNameTest() {Assertions.assertEquals("Macias", traineeList.get(0).getFirstName());}

        @Test
        @DisplayName("Last Name is Correct Test")
        void lastNameTest() {Assertions.assertEquals("Monroe", traineeList.get(0).getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2022-02-08", traineeList.get(0).getCourseStartDate());}

        @Test
        @DisplayName("Course End Date is Correct")
        void courseEndDateIsCorrect() {Assertions.assertEquals("2022-03-15", traineeList.get(0).getCourseEndDate());}

        @Test
        @DisplayName("Course ID is Correct")
        void courseIdIsCorrectTest() {Assertions.assertEquals(6, traineeList.get(0).getCourseId());}

        @Test
        @DisplayName("Trainee ID is Correct")
        void traineeIdIsCorrectTest() {Assertions.assertEquals("6202722fc96c0d99e85b30c2", traineeList.get(0).getId());}
    }

    @Nested
    @DisplayName("Crud Operation Tests")
    class CrudOperationTests {
        @Test
        @DisplayName("Posting a trainee")
        void postingATraineeTest() {
            TraineeForm newTrainee = new TraineeForm("dames", "borling", 5, "2023-02-05");
            sendTraineePostRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());
            System.out.println(makeUrl().spartan().getSpartanWithKey());

            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
            traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();
            Assertions.assertEquals("james", traineeList.get(traineeList.size()-1).getFirstName());
        }

        @Test
        @DisplayName("Putting a trainee")
        void puttingATrainee() {
            TraineeForm newTrainee = new TraineeForm("james", "dorling", 2, "2023-01-01");
            sendTraineePutRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());

            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
            traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();
            Assertions.assertEquals(2, traineeList.get(traineeList.size()-1).getCourseId());
        }

        @Test
        @DisplayName("Searching for a spartan by name")
        void searchingForASpartanByNameTest() {
            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().firstName("james").link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertEquals(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getFirstName(), "james");
        }
    }

}
