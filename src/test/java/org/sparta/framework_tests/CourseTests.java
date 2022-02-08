package org.sparta.framework_tests;

import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTOEnum;
import org.junit.jupiter.api.*;
import org.sparta.POJOs.Id;

import static org.sparta.framework.ConnectionManager.getStatusCode;
import static org.sparta.framework.Injector.injectDTOFromFile;

public class CourseTests {

    private CourseDTO courseDTO;

    @BeforeEach
    void init() {
        courseDTO = (CourseDTO) injectDTOFromFile("src/test/resources/json/courseExample.json", DTOEnum.COURSE);
    }

    @Nested
    @DisplayName("CourseDTO Tests")
    class CourseDTOTests {

        @Test
        @DisplayName("Course Id is not Null")
        void courseIdIsNotNull(){
            Assertions.assertTrue(courseDTO.courseIdIsNotNull());
        }

        @Test
        @DisplayName("Course Name is not Null")
        void courseNameIsNotNull(){
            Assertions.assertTrue(courseDTO.courseNameIsNotNull());
        }

        @Test
        @DisplayName("Course length is not Null")
        void courseLengthIsNotNull(){
            Assertions.assertTrue(courseDTO.lengthIsNotNull());
        }

        @Test
        @DisplayName("Course Description is not Null")
        void courseDescriptionIsNotNull(){
            Assertions.assertTrue(courseDTO.descriptionIsNotNull());
        }

        @Test
        @DisplayName("Object Id is not Null")
        void objectIdIsNotNull(){
            Assertions.assertTrue(courseDTO.idIsNotNull());
        }

        @Test
        @DisplayName("isActive is not Null")
        void isActiveIsNotNull(){
            Assertions.assertTrue(courseDTO.isActiveIsNotNull());
        }

    }

    @Nested
    @DisplayName("CoursePOJO Tests")
    class CoursePOJOTests {

        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode());
        }

        @Test
        @DisplayName("Id is an object Id")
        void idIsAnObjectId() {Assertions.assertInstanceOf(Id.class, courseDTO.getId());}

        @Test
        @DisplayName("Object Id is correct")
        void objectIdIsCorrect(){Assertions.assertEquals(new Id("620133dbb76917a6dcd28f17").getOid(), courseDTO.getId().getOid());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("Business", courseDTO.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(5, courseDTO.getLength());}

        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(6, courseDTO.getCourseId());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, courseDTO.isIsActive());}
    }

}
