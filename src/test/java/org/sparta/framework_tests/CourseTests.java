package org.sparta.framework_tests;

import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTOEnum;
import org.junit.jupiter.api.*;

import static org.sparta.framework.ConnectionManager.getStatusCode;
import static org.sparta.framework.Injector.injectDTOFromFile;

public class CourseTests {

    private CourseDTO courseDTO;

    @BeforeEach
    void init() {
        courseDTO = (CourseDTO) injectDTOFromFile("src/test/resources/json/courseExample.json", DTOEnum.COURSE);
    }

    @Nested
    @DisplayName("CoursePOJO Tests")
    class CoursePOJOTests {
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode());
        }

        @Test
        @DisplayName("Id is retrievable")
        void getIdTest(){Assertions.assertEquals("letters", courseDTO.getId());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("Java", courseDTO.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, courseDTO.getLength());}

        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(1, courseDTO.getCourseId());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, courseDTO.isIsActive());}
    }

}
