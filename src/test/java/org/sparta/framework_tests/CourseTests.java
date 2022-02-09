package org.sparta.framework_tests;

import org.junit.jupiter.api.*;
import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTOEnum;
import org.sparta.POJOs.CourseListPojos.CourseList;
import org.sparta.framework.connection.ConnectionManager;

import java.util.List;
import java.util.Objects;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.getStatusCode;

public class CourseTests {

    private static CourseDTO firstCourse;
    private static final String allCoursesURL = ConnectionManager.makeUrl().course().link();
    private static CourseDTO devOpsCourse;
    private static CourseDTO id2Course;

    @BeforeAll
    static void init() {
        CourseList courseDTOWrapper = (CourseList) injectDTO(allCoursesURL, DTOEnum.COURSE_LIST);
        List<CourseDTO> coursePojoList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        firstCourse = coursePojoList.get(0);
        for (CourseDTO course: coursePojoList) {
            if (Objects.equals(course.getCourseName(), "DevOps")){
                devOpsCourse = course;
            }
            if (course.getCourseId() == 2){
                id2Course = course;
            }
        }
    }

    @Nested
    @DisplayName("Checking injecting data to a DTO")
    class CourseDTOTests {

        @Test
        @DisplayName("Object Id is not Null")
        void objectIdIsNotNull(){
            Assertions.assertTrue(firstCourse.idIsNotNull());
        }

        @Test
        @DisplayName("Course Id is not Null")
        void courseIdIsNotNull(){
            Assertions.assertTrue(firstCourse.courseIdIsNotNull());
        }

        @Test
        @DisplayName("Course Name is not Null")
        void courseNameIsNotNull(){
            Assertions.assertTrue(firstCourse.courseNameIsNotNull());
        }

        @Test
        @DisplayName("Course length is not Null")
        void courseLengthIsNotNull(){
            Assertions.assertTrue(firstCourse.lengthIsNotNull());
        }

        @Test
        @DisplayName("Course Description is not Null")
        void courseDescriptionIsNotNull(){
            Assertions.assertTrue(firstCourse.descriptionIsNotNull());
        }

        @Test
        @DisplayName("isActive is not Null")
        void isActiveIsNotNull(){
            Assertions.assertTrue(firstCourse.isActiveIsNotNull());
        }
    }

    @Nested
    @DisplayName("Do we get expected values from get requests?")
    class CoursePOJOTests {

        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode(allCoursesURL));
        }

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){Assertions.assertEquals("6201338cb76917a6dcd28f15", devOpsCourse.getId());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("C#", id2Course.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, id2Course.getLength());}

        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(4, devOpsCourse.getCourseId());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, devOpsCourse.isIsActive());}
    }

}
