package org.sparta.framework_tests;

import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.POJOs.Id;
import org.junit.jupiter.api.*;
import org.sparta.framework.connection.ConnectionManager;

import java.time.LocalDate;

import static org.sparta.framework.connection.ConnectionManager.*;
import static org.sparta.framework.Injector.*;

public class TraineeTests {
    TraineeDTOList traineeDTOList;

    @BeforeEach
    void init() {
        traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan("6202722fc96c0d99e85b30c2"), DTOEnum.TRAINEE_LIST);
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
        void fullNameTest() {Assertions.assertEquals("Keri Valdez", traineeDTOList.getTraineeDTOList().get(0).getFullName());}

        @Test
        @DisplayName("StartDate as Date Returns Correct Date Test")
        void startDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 5), traineeDTOList.getTraineeDTOList().get(0).getStartDateAsDate());
        }

        @Test
        @DisplayName("EndDate as Date Returns Correct Date Test")
        void endDateAsDateTest() {
            Assertions.assertEquals(LocalDate.of(2022, 2, 4), traineeDTOList.getTraineeDTOList().get(0).getEndDateAsDate());
        }

        @Test
        @DisplayName("First Name is Not Null test")
        void firstNameNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).firstNameIsNotNull());}

        @Test
        @DisplayName("Last Name is Not Null test")
        void lastNameNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).lastNameIsNotNull());}

        @Test
        @DisplayName("Start Date is Not Null test")
        void startDateNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).startDateIsNotNull());}

        @Test
        @DisplayName("end Date is Not Null test")
        void endDateNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).endDateIsNotNull());}

        @Test
        @DisplayName("ID is Not Null test")
        void idNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).idIsNotNull());}

        @Test
        @DisplayName("Course ID is Not Null test")
        void courseIdNotNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).courseIdIsNotNull());}

        @Test
        @DisplayName("No Data is Null")
        void noDataIsNull() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).noDataIsNull());}

        @Test
        @DisplayName("Start Date is before End Date")
        void startIsBeforeEnd() {
            Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).startIsBeforeEnd());
        }

        @Test
        @DisplayName("End Date is after Start Date") //Redundant test, but if one fails and one passes something is up
        void endIsAfterStart() {
            Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).endIsAfterStart());
        }

        @Test
        @DisplayName("Start Date is after 0")
        void startIsAfterTheCalendarStarted() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).startIsAfter(LocalDate.of(2022, 1, 1)));}

        @Test
        @DisplayName("End Date is before the set max date")
        void endIsBeforeTheEndOfTime() {Assertions.assertTrue(traineeDTOList.getTraineeDTOList().get(0).endIsBefore(LocalDate.of(2050, 12, 31)));}

        @Test
        @DisplayName("Course Name is correct")
        void courseNameIsCorrect() {Assertions.assertEquals("java", traineeDTOList.getTraineeDTOList().get(0).getCourseName());}
    }

    @Nested
    @DisplayName("TraineePOJO Tests")
    class TraineePojoTests {
        @Test
        @DisplayName("First Name is Correct Test")
        void firstNameTest() {Assertions.assertEquals("Keri", traineeDTOList.getTraineeDTOList().get(0).getFirstName());}

        @Test
        @DisplayName("Last Name is Correct Test")
        void lastNameTest() {Assertions.assertEquals("Valdez", traineeDTOList.getTraineeDTOList().get(0).getLastName());}

        @Test
        @DisplayName("Course Start Date is Correct")
        void courseStartIsCorrect() {Assertions.assertEquals("2022-02-05", traineeDTOList.getTraineeDTOList().get(0).getCourseStartDate());}

        @Test
        @DisplayName("Course End Date is Correct")
        void courseEndDateIsCorrect() {Assertions.assertEquals("2022-02-04", traineeDTOList.getTraineeDTOList().get(0).getCourseEndDate());}

        @Test
        @DisplayName("Course ID is Correct")
        void courseIdIsCorrectTest() {Assertions.assertEquals(1, traineeDTOList.getTraineeDTOList().get(0).getCourseId());}

        @Test
        @DisplayName("Trainee ID is an ID")
        void traineeIdIsAnIdTest() {Assertions.assertInstanceOf(Id.class, traineeDTOList.getTraineeDTOList().get(0).getId());}

        @Test
        @DisplayName("Trainee ID is Correct")
        void traineeIdIsCorrectTest() {Assertions.assertEquals(new Id("620132158e281a4c868efd1d").getOid(), traineeDTOList.getTraineeDTOList().get(0).getId().getOid());}
    }
}
