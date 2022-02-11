package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.*;
import org.sparta.crud_forms.AddCourseForm;
import org.sparta.crud_forms.AddTraineeForm;
import org.sparta.crud_forms.UpdateCourseForm;
import org.sparta.framework.Injector;
import org.sparta.framework.connection.ConnectionManager;


import java.util.Objects;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.*;

public class CourseStepDef {

    private String courseObjectID;
    private Integer courseId;
    private String courseName;
    private String description;
    private Integer length;
    private boolean active;

    private String firstName;
    private String lastName;
    private String startDate;

    private TraineeDTO trainee;

    private AddCourseForm courseForm;

    private AddTraineeForm dummyTrainee = new AddTraineeForm("new", "dummy", 6, "2022-04-15");
    private UpdateCourseForm dummyCourseForm = new UpdateCourseForm("randomcourseid", 6, "newcourse", 1, "dummy course", true);

    @Given("I am creating a new course")
    public void iAmCreatingANewCourse() {
        courseId = 6;
        courseName = "new course";
        description = "dummy course";
        length = 5;
        active = true;

        courseForm = new AddCourseForm(courseName, length, description);
    }

    @When("I enter a name and length")
    public void iEnterANameAndLength() {
        courseName = "new course";
        length = 2;

    }

    @Then("I want to be able to assign trainees to it")
    public void iWantToBeAbleToAssignTraineesToIt() {
        sendPostRequest(courseForm.getJson(), ConnectionManager.makeUrl().getCourseWithKey());

        firstName = "test";
        lastName = "dummy";
        startDate = "2022-04-15";
        AddTraineeForm newTrainee = new AddTraineeForm(firstName, lastName, courseId, startDate);
        sendPostRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());

        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().courseName("new course").link(), DTOEnum.TRAINEE_LIST);
        Assertions.assertEquals(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getCourseName(), courseName);
    }

    @When("I send a put request to a course endpoint")
    public void iSendAPutRequestToACourseEndpoint() {
        sendPutRequest(courseForm.getJson(), ConnectionManager.makeUrl().getCourseWithKey());
    }

    //Then I should be able to update the course length
    @Then("I should be able to update the course length")
    public void iShouldBeAbleToUpdateTheCourseLength() {
        CourseDTOList courseDTOList = (CourseDTOList) Injector.injectDTO(ConnectionManager.makeUrl().course().link(), DTOEnum.COURSE_LIST);
        for(CourseDTO courseDTO: courseDTOList.getEmbedded().getCourseDTOList()) {
            if(Objects.equals(courseDTO.getCourseName(), courseName))
                Assertions.assertNotEquals(courseDTO.getLength(), length);
        }
    }

    @Then("I should be able to update a trainee's end date")
    public void iShouldBeAbleToUpdateATraineeSEndDate() {

    }


    @When("I send a put request to a course endpoint that updates the course endpoint")
    public void iSendAPutRequestToACourseEndpointThatUpdatesTheCourseEndpoint() {
        sendPutRequest(new UpdateCourseForm(courseObjectID, null, null, 2, null, null).getJson(), makeUrl().getCourseWithKey());
    }

    @Then("All trainees on that course should have their end date adjusted")
    public void allTraineesOnThatCourseShouldHaveTheirEndDateAdjusted() {
        TraineeDTO updatedTrainee = (TraineeDTO) injectDTO(makeUrl().getSpecificSpartan(trainee.getId()), DTOEnum.TRAINEE);
        Assertions.assertEquals(trainee.getEndDateAsDate().plusWeeks(1), updatedTrainee.getEndDateAsDate());
    }

    @When("I create a new course with a trainee on it")
    public void iCreateANewCourse() {
        String[] response = sendPostRequest(new AddCourseForm("testCourse", 1, "A course for testing.").getJson(),
                ConnectionManager.makeUrl().getCourseWithKey()).body().split(",");
        courseObjectID = response[0].split(":")[1].replace("\"", "");
        courseId = Integer.parseInt(response[1].split(":")[1].replace("\"", ""));
        trainee = (TraineeDTO) injectDTO(sendPostRequest(new AddTraineeForm("test", "ok-to-delete", courseId,"23-01-01").getJson(), makeUrl().getSpartanWithKey()).body(), DTOEnum.TRAINEE);
    }
}
