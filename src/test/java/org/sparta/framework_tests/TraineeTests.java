package org.sparta.framework_tests;

import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.junit.jupiter.api.*;
import org.sparta.crud_forms.AddTraineeForm;
import org.sparta.crud_forms.UpdateTraineeForm;
import org.sparta.framework.connection.ConnectionManager;

import java.time.LocalDate;
import java.util.List;

import static org.sparta.framework.connection.ConnectionManager.*;
import static org.sparta.framework.Injector.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TraineeTests {
    private TraineeDTO trainee;
    private final boolean editDatabase = false; //Temporary boolean so I do not add an employee called "dames" every time I run tests

    @BeforeEach
    void init() {
        trainee = (TraineeDTO) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan("6203af8f51765e2883463d96"), DTOEnum.TRAINEE);
    }

    @Nested
    @Order(value = 3)
    @DisplayName("TraineeDTO Tests")
    class TraineeDTOTests {
        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode());
        }

        @Test
        @DisplayName("Name is Correct Test")
        void fullNameTest() {Assertions.assertEquals("james dorling", trainee.getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2023, 1, 1), trainee.getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2023, 2, 26), trainee.getEndDateAsDate());
        }

        @Test
        @DisplayName("First Name is Not Null test")
        void firstNameNotNull() {Assertions.assertTrue(trainee.firstNameIsNotNull());}

        @Test
        @DisplayName("Last Name is Not Null test")
        void lastNameNotNull() {Assertions.assertTrue(trainee.lastNameIsNotNull());}

        @Test
        @DisplayName("Start Date is Not Null test")
        void startDateNotNull() {Assertions.assertTrue(trainee.startDateIsNotNull());}

        @Test
        @DisplayName("end Date is Not Null test")
        void endDateNotNull() {Assertions.assertTrue(trainee.endDateIsNotNull());}

        @Test
        @DisplayName("ID is Not Null test")
        void idNotNull() {Assertions.assertTrue(trainee.idIsNotNull());}

        @Test
        @DisplayName("Course ID is Not Null test")
        void courseIdNotNull() {Assertions.assertTrue(trainee.courseIdIsNotNull());}

        @Test
        @DisplayName("No Data is Null")
        void noDataIsNull() {Assertions.assertTrue(trainee.noDataIsNull());}

        @Test
        @DisplayName("Start Date is before End Date")
        void startIsBeforeEnd() {
            Assertions.assertTrue(trainee.startIsBeforeEnd());
        }

        @Test
        @DisplayName("End Date is after Start Date") //Redundant test, but if one fails and one passes something is up
        void endIsAfterStart() {
            Assertions.assertTrue(trainee.endIsAfterStart());
        }

        @Test
        @DisplayName("Start Date is after 0")
        void startIsAfterTheCalendarStarted() {Assertions.assertTrue(trainee.startIsAfter(LocalDate.of(2022, 1, 1)));}

        @Test
        @DisplayName("End Date is before the set max date")
        void endIsBeforeTheEndOfTime() {Assertions.assertTrue(trainee.endIsBefore(LocalDate.of(2050, 12, 31)));}

        @Test
        @DisplayName("Course Name is correct")
        void courseNameIsCorrect() {Assertions.assertEquals("c#", trainee.getCourseName());} //Im not on this course but still
    }

    @Nested
    @Order(value = 2)
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Correct Test")
        void firstNameTest() {Assertions.assertEquals("james", trainee.getFirstName());}

        @Test
        @DisplayName("Last Name is Correct Test")
        void lastNameTest() {Assertions.assertEquals("dorling", trainee.getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2023-01-01", trainee.getCourseStartDate());}

        @Test
        @DisplayName("Course End Date is Correct")
        void courseEndDateIsCorrect() {Assertions.assertEquals("2023-02-26", trainee.getCourseEndDate());}

        @Test
        @DisplayName("Course ID is Correct")
        void courseIdIsCorrectTest() {Assertions.assertEquals(2, trainee.getCourseId());}

        @Test
        @DisplayName("Trainee ID is Correct")
        void traineeIdIsCorrectTest() {Assertions.assertEquals("6203af8f51765e2883463d96", trainee.getId());}
    }

    @Nested
    @Order(value = 1)
    @DisplayName("Crud Operation Tests")
    class CrudOperationTests {
        @Test
        @DisplayName("Get Trainee By ID")
        void getTraineeById() {
            Assertions.assertNotNull(trainee);
        }
        
        @Test
        @DisplayName("Posting a trainee")
        void postingATraineeTest() {
            Assumptions.assumeTrue(editDatabase);
            AddTraineeForm newTrainee = new AddTraineeForm("dames", "borling", 5, "2023-02-05");
            sendTraineePostRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());
            System.out.println(makeUrl().spartan().getSpartanWithKey());

            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertEquals("dames", traineeDTOList.getEmbedded().getSpartanEntityList().get(
                    traineeDTOList.getEmbedded().getSpartanEntityList().size()-1).getFirstName());
        }

        @Test
        @DisplayName("Putting a trainee")
        void puttingATrainee() {
            Assumptions.assumeTrue(editDatabase);
            UpdateTraineeForm updateTrainee = new UpdateTraineeForm("6203af8f51765e2883463d96","james", "dorling", 2, "2023-01-01");
            sendTraineePutRequest(updateTrainee.getJson(), makeUrl().getSpartanWithKey());

            TraineeDTO editedTrainee = (TraineeDTO) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan("6203af8f51765e2883463d96"), DTOEnum.TRAINEE);
            Assertions.assertEquals(2, editedTrainee.getCourseId());
        }

        @Test
        @DisplayName("Searching for a spartan by first name")
        void searchingForASpartanByFirstNameTest() {
            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().firstName("james").link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertEquals(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getFirstName(), "james");
        }

        @Test
        @DisplayName("Searching for a spartan by last name")
        void searchingForASpartanByLastName() {
            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().lastName("james").link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertEquals(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getLastName(), "dorling");
        }

        @Test
        @DisplayName("Searching for a spartan by date before")
        void searchingForASpartanByDateBefore() {
            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                    .BeforeAfter(UrlBuilder.TimeParameters.BEFORE).StartEnd(UrlBuilder.TimeParameters.START).date("2025-01-01").link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertTrue(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getStartDateAsDate()
                    .isBefore(LocalDate.of(2025, 1,1)));
        }

        @Test
        @DisplayName("Searching for a spartan by date after")
        void searchingForASpartanByDateAfter() {
            TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                    .BeforeAfter(UrlBuilder.TimeParameters.AFTER).StartEnd(UrlBuilder.TimeParameters.END).date("2021-01-01").link(), DTOEnum.TRAINEE_LIST);
            Assertions.assertTrue(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getEndDateAsDate()
                    .isAfter(LocalDate.of(2021, 1,1)));
        }
    }
}