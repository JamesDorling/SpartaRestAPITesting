package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.crud_forms.AddCourseForm;
import org.sparta.crud_forms.AddTraineeForm;
import org.sparta.framework.connection.ConnectionManager;


import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.makeUrl;
import static org.sparta.framework.connection.ConnectionManager.sendTraineePostRequest;

public class CourseStepDef {

    private Integer courseId;
    private String courseName;
    private String description;
    private Integer length;
    private boolean active;

    private String firstName;
    private String lastName;
    private String startDate;

    private AddCourseForm courseForm;

    @Given("I am creating a new course")
    public void iAmCreatingANewCourse() {
        courseId = 6;
        courseName = "new course";
        description = "dummy course";
        length = 5;
        active = true;

        courseForm = new AddCourseForm(courseId, courseName, description, length, active);
    }

    @When("I enter a name and length")
    public void iEnterANameAndLength() {
        courseName = "new course";
        length = 2;

    }

    @Then("I want to be able to assign trainees to it")
    public void iWantToBeAbleToAssignTraineesToIt() {
        ConnectionManager.sendCoursePostRequest(courseForm.getCourseJson(), ConnectionManager.makeUrl().getCourseWithKey());

        firstName = "test";
        lastName = "dummy";
        startDate = "2022-04-15";
        AddTraineeForm newTrainee = new AddTraineeForm(firstName, lastName, courseId, startDate);
        sendTraineePostRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());

        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().courseName("new course").link(), DTOEnum.TRAINEE_LIST);
        Assertions.assertEquals(traineeDTOList.getEmbedded().getSpartanEntityList().get(0).getCourseName(), courseName);
    }

    @When("I send a put request to a course endpoint")
    public void iSendAPutRequestToACourseEndpoint() {
        ConnectionManager.sendCoursePutRequest(courseForm.getCourseJson(), ConnectionManager.makeUrl().getCourseWithKey());
    }
}
