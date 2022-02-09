package org.sparta.framework_tests;

import org.junit.jupiter.api.*;
import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTOEnum;
import org.sparta.POJOs.CourseListPojos.CourseList;
import org.sparta.POJOs.CourseListPojos.HATAEOSExtension.CourseSpartanLinks;
import org.sparta.framework.connection.ConnectionManager;

import java.util.List;
import java.util.Objects;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.getStatusCode;

public class CourseTests {

    private static final String allCoursesURL = ConnectionManager.makeUrl().course().link();
    private static List<CourseDTO> allCoursesList;
    private static CourseDTO firstCourse;
    private static CourseDTO devOpsCourse;
    private static CourseDTO id2Course;

    private static final String getCourseID3URL = ConnectionManager.makeUrl().getSpecificCourse(3);
    private static CourseDTO id3Course;

    private static final String getAllActiveCoursesURL = ConnectionManager.makeUrl().getAllActiveCourses();
    private static List<CourseDTO> allActiveCoursesList;

    private static final String getAllInactiveCoursesURL = ConnectionManager.makeUrl().getAllInactiveCourses();
    private static List<CourseDTO> allInactiveCoursesList;

    //
    private static final String courseName = "Java";
    private static final String getCourseByName = ConnectionManager.makeUrl().course().courseName(courseName).link();
    private static List<CourseDTO> courseWithName;
    private static final String partialCourseName = "a";
    private static final String getCourseByPartialName = ConnectionManager.makeUrl().course().courseName(partialCourseName).link();
    private static List<CourseDTO> courseWithPartialName;

    @BeforeAll
    static void init() {
        CourseList courseDTOWrapper = (CourseList) injectDTO(allCoursesURL, DTOEnum.COURSE_LIST);
        allCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        firstCourse = allCoursesList.get(0);
        for (CourseDTO course: allCoursesList) {
            if (Objects.equals(course.getCourseName(), "DevOps")){
                devOpsCourse = course;
            }
            if (course.getCourseId() == 2){
                id2Course = course;
            }
        }
        
        id3Course = (CourseDTO) injectDTO(getCourseID3URL, DTOEnum.COURSE);
        
        courseDTOWrapper = (CourseList) injectDTO(getAllActiveCoursesURL, DTOEnum.COURSE_LIST);
        if (courseDTOWrapper.getEmbedded()!= null){
            allActiveCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }

        courseDTOWrapper = (CourseList) injectDTO(getAllInactiveCoursesURL, DTOEnum.COURSE_LIST);
        if (courseDTOWrapper.getEmbedded()!= null) {
            allInactiveCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }

        courseDTOWrapper = (CourseList) injectDTO(getCourseByName, DTOEnum.COURSE_LIST);
        if (courseDTOWrapper.getEmbedded()!= null) {
            courseWithName = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }
        courseDTOWrapper = (CourseList) injectDTO(getCourseByPartialName, DTOEnum.COURSE_LIST);
        if (courseDTOWrapper.getEmbedded()!= null) {
            courseWithPartialName = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }
    }

    @Nested
    @DisplayName("Checking injecting data to a DTO")
    class InjectingToDTOTests {

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

        @Test
        @DisplayName("links is not Null")
        void linksIsNotNull(){
            Assertions.assertTrue(firstCourse.linksIsNotNull());
        }
    }

    @Nested
    @DisplayName("Do we get expected values from a get all request?")
    class GetAllCoursesTests {

        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode(allCoursesURL));
        }

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){Assertions.assertEquals("6203a71b147baa0d50338f5a", devOpsCourse.getId());}

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

    @Nested
    @DisplayName("Do we get expected values from a get by id request?")
    class GetCourseByIDTests{
        @Test
        @DisplayName("Successful Connection Test")
        void connectionCode200Test() {
            Assertions.assertEquals(200, getStatusCode(getCourseID3URL));
        }

        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(3, id3Course.getCourseId());}

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){Assertions.assertEquals("62013367b76917a6dcd28f14", id3Course.getId());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("Data", id3Course.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, id3Course.getLength());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, id3Course.isIsActive());}
        
        @Test
        @DisplayName("Can HATEOAS links be found? ")
        void canHateoasLinksBeFound() {
            Assertions.assertTrue(id3Course.getLinks().getSpartanList().size() > 0);
        }
        
        @Test
        @DisplayName("Do HATEOAS links lead to valid URLs?")
        void doHateoasLinksLeadToValidUrLs() {
            for (CourseSpartanLinks courseSpartanLinks : id3Course.getLinks().getSpartanList()) {
                Assertions.assertEquals(200, getStatusCode(courseSpartanLinks.getHref()));
            }
        }
        
    }

    @Nested
    @DisplayName("Testing getting all active/inactive courses")
    class TestingGettingAllActiveInactiveCourses {

        @Test
        @DisplayName("Successful Connection Test Active")
        void connectionCode200TestActive() {
            Assertions.assertEquals(200, getStatusCode(getAllActiveCoursesURL));
        }

        @Test
        @DisplayName("Successful Connection Test Inactive")
        void connectionCode200TestInactive() {
            Assertions.assertEquals(200, getStatusCode(getAllInactiveCoursesURL));
        }

        @Test
        @DisplayName("Are all of the returned active courses actually active?")
        void areAllOfTheReturnedActiveCoursesActuallyActive() {
            Assumptions.assumeFalse(allActiveCoursesList == null);
            for (CourseDTO course: allActiveCoursesList) {
                Assertions.assertTrue(course.isIsActive());
            }
        }

        @Test
        @DisplayName("Are all of the returned inactive courses actually inactive?")
        void areAllOfTheReturnedInactiveCoursesActuallyInactive() {
            Assumptions.assumeFalse(allInactiveCoursesList == null);
            for (CourseDTO course: allInactiveCoursesList) {
                Assertions.assertFalse(course.isIsActive());
            }
        }
    }

    @Nested
    @DisplayName("Test searching for courses by name")
    class TestSearchingForCoursesByName {

        @Test
        @DisplayName("Successful Connection Test Full Name")
        void connectionCode200TestFull() {
            Assertions.assertEquals(200, getStatusCode(getCourseByName));
        }

        @Test
        @DisplayName("Successful Connection Test Partial Name")
        void connectionCode200TestPartial() {
            Assertions.assertEquals(200, getStatusCode(getCourseByPartialName));
        }

        @Test
        @DisplayName("When searching with an exact name, is exactly one course found?")
        void whenSearchingWithAnExactNameIsExactlyOneCourseFound() {
            Assertions.assertEquals(1, courseWithName.size());
        }

        @Test
        @DisplayName("Does searching by exact name bring up the expected course?")
        void doesSearchingByExactNameBringUpTheExpectedCourse() {
            CourseDTO foundCourse = courseWithName.get(0);
            Assertions.assertEquals(courseName,foundCourse.getCourseName());
        }

        @Test
        @DisplayName("When searching by partial name, do all matches contain the string?")
        void whenSearchingByPartialNameDoAllMatchesContainTheString() {
            Assumptions.assumeFalse(courseWithPartialName == null);
            for (CourseDTO courseDTO : courseWithPartialName) {
                Assertions.assertTrue(courseDTO.getCourseName().contains(partialCourseName));
            }
        }
    }

}
