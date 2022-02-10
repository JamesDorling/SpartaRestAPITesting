package org.sparta.framework_tests;

import org.junit.jupiter.api.*;
import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTOEnum;
import org.sparta.POJOs.CourseListPojos.CourseList;
import org.sparta.POJOs.CourseListPojos.HATAEOSExtension.CourseSpartanLinks;
import org.sparta.crud_forms.AddCourseForm;
import org.sparta.crud_forms.UpdateCourseForm;
import org.sparta.framework.connection.ConnectionManager;

import java.util.List;
import java.util.Objects;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.*;

public class CourseTests {

    private static final String allCoursesURL = ConnectionManager.makeUrl().course().link();
    private static List<CourseDTO> allCoursesList;
    private static CourseDTO firstCourse;
    private static CourseDTO devOpsCourse;
    private static CourseDTO id2Course;

    private static final String getSensibleCourseIDURL = ConnectionManager.makeUrl().getSpecificCourse(3);
    private static CourseDTO sensibleIdCourse;
    private static final String getSillyCourseIDURL = ConnectionManager.makeUrl().getSpecificCourse(-999);

    private static final String getAllActiveCoursesURL = ConnectionManager.makeUrl().getAllActiveCourses();
    private static List<CourseDTO> allActiveCoursesList;

    private static final String getAllInactiveCoursesURL = ConnectionManager.makeUrl().getAllInactiveCourses();
    private static List<CourseDTO> allInactiveCoursesList;

    private static final String courseWithKeyURL = ConnectionManager.makeUrl().getCourseWithKey();
    private static CourseDTO postCourse;
    private static String newCourseJson;
    private static String getPostCourseURL;
    private static CourseDTO putCourse;
    private static String putCourseJson;
    private static String getPutCourseURL;


    private static final String courseName = "Java";
    private static final String getCourseByName = ConnectionManager.makeUrl().course().courseName(courseName).link();
    private static List<CourseDTO> courseWithName;
    private static final String partialCourseName = "a";
    private static final String getCourseByPartialName = ConnectionManager.makeUrl().course().courseName(partialCourseName).link();
    private static List<CourseDTO> courseWithPartialName;
    private static final String wrongCourseName = "sailkudfghaslkjfdhlkashdfa";
    private static final String getCourseBySillyName = ConnectionManager.makeUrl().course().courseName(wrongCourseName).link();


    @BeforeAll
    static void init() {
        CourseList courseDTOWrapper = (CourseList) injectDTO(allCoursesURL, DTOEnum.COURSE_LIST);
        allCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        for (CourseDTO course: allCoursesList) {
            if (Objects.equals(course.getCourseName(), "DevOps")){
                devOpsCourse = course;
            }
            if (course.getCourseId() == 2){
                id2Course = course;
            }
        }

        if (getStatusCode(getSensibleCourseIDURL)==200) {
            sensibleIdCourse = (CourseDTO) injectDTO(getSensibleCourseIDURL, DTOEnum.COURSE);
        }

        if (getStatusCode(getAllActiveCoursesURL)==200){
            courseDTOWrapper = (CourseList) injectDTO(getAllActiveCoursesURL, DTOEnum.COURSE_LIST);
            allActiveCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }

        if (getStatusCode(getAllInactiveCoursesURL)==200){
            courseDTOWrapper = (CourseList) injectDTO(getAllInactiveCoursesURL, DTOEnum.COURSE_LIST);
            allInactiveCoursesList = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }

        if (getStatusCode(getCourseByName)==200){
            courseDTOWrapper = (CourseList) injectDTO(getCourseByName, DTOEnum.COURSE_LIST);
            courseWithName = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }

        if (getStatusCode(getCourseByPartialName)==200){
            courseDTOWrapper = (CourseList) injectDTO(getCourseByPartialName, DTOEnum.COURSE_LIST);
            courseWithPartialName = courseDTOWrapper.getEmbedded().getCourseDTOList();
        }
      
        // Once there's a Course DeleteMapping, maybe change the example courseName and description
        newCourseJson = new AddCourseForm("WeNeedACourseDeleteMapping", 8, "WeNeedACourseDeleteMapping").getJson();
        sendPostRequest(newCourseJson, courseWithKeyURL);
        // Also below section of code should likely have allCoursesList.size() + 1 instead,
        // but that should only be changed once admin layer by devs is complete
        getPostCourseURL = ConnectionManager.makeUrl().getSpecificCourse(allCoursesList.size());
        postCourse = (CourseDTO) injectDTO(getPostCourseURL, DTOEnum.COURSE);

        putCourseJson = new UpdateCourseForm("6203d66702673b3a3eccc999", 7, "JavaScript", 8, "Javascript", true).getJson();
        sendPutRequest(putCourseJson, courseWithKeyURL);

        getPutCourseURL = ConnectionManager.makeUrl().getSpecificCourse(7);
        putCourse = (CourseDTO) injectDTO(getPutCourseURL, DTOEnum.COURSE);
    }

    @Nested
    @DisplayName("Checking injecting data to a DTO")
    class InjectingToDTOTests {

        @Test
        @DisplayName("Object Id is not Null")
        void objectIdIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.idIsNotNull());
        }

        @Test
        @DisplayName("Course Id is not Null")
        void courseIdIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.courseIdIsNotNull());
        }

        @Test
        @DisplayName("Course Name is not Null")
        void courseNameIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.courseNameIsNotNull());
        }

        @Test
        @DisplayName("Course length is not Null")
        void courseLengthIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.lengthIsNotNull());
        }

        @Test
        @DisplayName("Course Description is not Null")
        void courseDescriptionIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.descriptionIsNotNull());
        }

        @Test
        @DisplayName("isActive is not Null")
        void isActiveIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.isActiveIsNotNull());
        }

        @Test
        @DisplayName("links is not Null")
        void linksIsNotNull(){
            Assertions.assertTrue(sensibleIdCourse.linksIsNotNull());
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
        @DisplayName("Successful connection with sensible ID")
        void successfulConnectionWithSensibleId() {
            Assertions.assertEquals(200, getStatusCode(getSensibleCourseIDURL));
        }

        @Test
        @DisplayName("Failed connection with silly ID")
        void failedConnectionWithSillyId() {
            Assertions.assertEquals(400, getStatusCode(getSillyCourseIDURL));
        }

        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(3, sensibleIdCourse.getCourseId());}

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){Assertions.assertEquals("62013367b76917a6dcd28f14", sensibleIdCourse.getId());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("Data", sensibleIdCourse.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, sensibleIdCourse.getLength());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, sensibleIdCourse.isIsActive());}
        
        @Test
        @DisplayName("Can HATEOAS links be found? ")
        void canHateoasLinksBeFound() {
            Assertions.assertTrue(sensibleIdCourse.getLinks().getSpartanList().size() > 0);
        }
        
        @Test
        @DisplayName("Do HATEOAS links lead to valid URLs?")
        void doHateoasLinksLeadToValidUrLs() {
            for (CourseSpartanLinks courseSpartanLinks : sensibleIdCourse.getLinks().getSpartanList()) {
                Assertions.assertEquals(200, getStatusCode(courseSpartanLinks.getHref()));
            }
        }
        
    }

    @Nested
    @DisplayName("Testing to see if post function works properly")
    class PostCourseTests {

        @Test
        @DisplayName("Do we get an error without using an API key?")
        void doWeGetAnErrorWithoutUsingAnApiKey() {
            Assertions.assertEquals(400,sendPutRequest(newCourseJson, allCoursesURL).statusCode());
        }

        @Test
        @DisplayName("Course Id is retrievable")
            // Also below section of code should likely have allCoursesList.size() + 1 instead,
            // but that should only be changed once admin layer by devs is complete
        void getCourseIdTest(){Assertions.assertEquals(allCoursesList.size(), postCourse.getCourseId());}

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){
            Assertions.assertEquals(24, postCourse.getId().length());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("WeNeedACourseDeleteMapping", postCourse.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, postCourse.getLength());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, postCourse.isIsActive());}
    }

    @Nested
    @DisplayName("Does using put function update course properly?")
    class PutCourseTests {
        @Test
        @DisplayName("Course Id is retrievable")
        void getCourseIdTest(){Assertions.assertEquals(7, putCourse.getCourseId());}

        @Test
        @DisplayName("Object id is retrievable")
        void getObjectIDTest(){
            Assertions.assertEquals("6203d66702673b3a3eccc999", putCourse.getId());}

        @Test
        @DisplayName("Course name is retrievable")
        void getCourseNameTest(){Assertions.assertEquals("JavaScript", putCourse.getCourseName());}

        @Test
        @DisplayName("Length of course is retrievable")
        void getLengthOfCourseTest(){Assertions.assertEquals(8, putCourse.getLength());}

        @Test
        @DisplayName("Activation status is retrievable")
        void getActivationStatusTest(){Assertions.assertEquals(true, putCourse.isIsActive());}

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
        @DisplayName("Successful Connection with Full Name")
        void connectionCode200TestFull() {
            Assertions.assertEquals(200, getStatusCode(getCourseByName));
        }

        @Test
        @DisplayName("Successful Connection with Partial Name")
        void connectionCode200TestPartial() {
            Assertions.assertEquals(200, getStatusCode(getCourseByPartialName));
        }

        @Test
        @DisplayName("Failed connection with silly name")
        void failedConnectionWithSillyName() {
            Assertions.assertEquals(400, getStatusCode(getCourseBySillyName));
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

    @Nested
    @DisplayName("POSTing courses")
    class PosTingCourses {
        @Test
        @DisplayName("Do we get an error without using an API key?")
        void doWeGetAnErrorWithoutUsingAnApiKey() {
            Assertions.assertEquals(400,sendPostRequest(newCourseJson, allCoursesURL).statusCode());
        }
    }
}
