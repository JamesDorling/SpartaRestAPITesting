package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.*;
import org.sparta.framework.connection.UrlBuilder;

import static org.sparta.framework.Injector.*;
import static org.sparta.framework.connection.ConnectionManager.*;

public class SearchStepdefs {
    private TraineeDTO trainee;
    private TraineeDTOList traineeList;

    @When("I send a GET request with course name")
    public void iSendAGETRequestWithCourseName() {
        traineeList = (TraineeDTOList) injectDTO(makeUrl().course().courseName("java").link(), DTOEnum.COURSE_LIST);
    }

    @Then("I should receive all trainees on that course")
    public void iShouldReceiveAllTraineesOnThatCourse() {
        boolean courseCorrect = true;
        for (TraineeDTO traineeDTO : traineeList.getEmbedded().getSpartanEntityList()) {
            if(!(traineeDTO.getCourseId() == 1)) {
                courseCorrect = false;
                break;
            }
        }
        Assertions.assertTrue(courseCorrect);
    }

    @When("I send a GET request with a start date")
    public void iSendAGETRequestWithAStartDate() {
        traineeList = (TraineeDTOList) injectDTO(makeUrl().spartan().StartEnd(UrlBuilder.TimeParameters.START)
                .BeforeAfter(UrlBuilder.TimeParameters.SAME).date("2022-02-08").link(), DTOEnum.TRAINEE_LIST);
    }

    @Then("I should receive all trainees who started ON that date")
    public void iShouldReceiveAllTraineesWhoStartedONThatDate() {
        boolean datesCorrect = true;
        for (TraineeDTO traineeDTO : traineeList.getEmbedded().getSpartanEntityList()) {
            if(!traineeDTO.getCourseStartDate().equals("2022-02-08")) {
                datesCorrect = false;
                break;
            }
        }
        Assertions.assertTrue(datesCorrect);
    }

    @When("I send a GET request with first name {string} and last name {string}")
    public void iSendAGETRequestWithFirstNameAndLastName(String arg0, String arg1) {
        traineeList = (TraineeDTOList) injectDTO(makeUrl().spartan().firstName(arg0).lastName(arg1).link(), DTOEnum.TRAINEE_LIST);
    }

    @Then("I should receive all trainees called {string} {string}")
    public void iShouldReceiveAllTraineesCalled(String arg0, String arg1) {
        boolean namesAreCorrect = true;
        for (TraineeDTO traineeDTO : traineeList.getEmbedded().getSpartanEntityList()) {
            if(!traineeDTO.getFullName().equals(arg0 + " " + arg1)) {
                namesAreCorrect = false;
                break;
            }
        }
        Assertions.assertTrue(namesAreCorrect);
    }

    @When("I send a GET request with an end date")
    public void iSendAGETRequestWithAnEndDate() {
        traineeList = (TraineeDTOList) injectDTO(makeUrl().spartan().StartEnd(UrlBuilder.TimeParameters.END)
                .BeforeAfter(UrlBuilder.TimeParameters.SAME).date("2022-04-15").link(), DTOEnum.TRAINEE_LIST);
    }

    @Then("I should receive all trainees that finish ON that date")
    public void iShouldReceiveAllTraineesThatFinishONThatDate() {
        boolean datesCorrect = true;
        for (TraineeDTO traineeDTO : traineeList.getEmbedded().getSpartanEntityList()) {
            if(!traineeDTO.getCourseEndDate().equals("2022-04-15")) {
                datesCorrect = false;
                break;
            }
        }
        Assertions.assertTrue(datesCorrect);
    }

    @When("I send a GET request with an ID endpoint")
    public void iSendAGETRequestWithAnIDEndpoint() {
        trainee = (TraineeDTO) injectDTO(makeUrl().getSpecificSpartan("6204510700635654a35f083a"), DTOEnum.TRAINEE);
    }

    @Then("I should receive back the trainee with that ID")
    public void iShouldReceiveBackTheTraineeWithThatID() {
        Assertions.assertEquals("6204510700635654a35f083a", trainee.getId());
    }
}
