package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.crud_forms.AddTraineeForm;
import org.sparta.crud_forms.UpdateTraineeForm;
import org.sparta.framework.connection.ConnectionManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.*;

public class TraineeStepdefs {

    static TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
    static List<TraineeDTO> traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();

    private String firstName;
    private String lastName;
    private int courseId;
    private String startDate;
    private String endDate;
    private LocalDate dateQuery;


    @When("I enter first name {string}")
    public void iEnterFirstName(String arg0) {
        firstName = arg0;
    }

    @And("I enter last name of {string}")
    public void iEnterLastNameOf(String arg0) {
        lastName = arg0;
    }

    @And("I enter course ID of {int}")
    public void iEnterCourseIDOf(int arg0) {
        courseId = arg0;
    }

    @And("I enter start date of {string}")
    public void iEnterStartDateOf(String arg0) {
        startDate = arg0;
    }

    @And("I send a POST request")
    public void iSendAPOSTRequest() {
        AddTraineeForm newTrainee = new AddTraineeForm(firstName, lastName, courseId, startDate);
        System.out.println("Generated trainee form: " + newTrainee.getJson());
        sendTraineePostRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());
    }

    @Then("A trainee should be created with the name {string} {string}, a course ID of {int}, a start date of {string}, and an end date of {string}")
    public void aTraineeShouldBeCreatedWithTheNameACourseIDOfAStartDateOfAndAnEndDateOf(String arg0, String arg1, int arg2, String arg3, String arg4) {

        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
        traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();

        ArrayList<String> expected = new ArrayList<>();
        expected.add(arg0);
        expected.add(arg1);
        expected.add(String.valueOf(arg2));
        expected.add(arg3);
        expected.add(arg4);

        System.out.println("Expected Array: " + expected);

        ArrayList<String> received = new ArrayList<>();
        received.add(traineeList.get(traineeList.size()-1).getFirstName());
        received.add(traineeList.get(traineeList.size()-1).getLastName());
        received.add(String.valueOf(traineeList.get(traineeList.size()-1).getCourseId()));
        received.add(traineeList.get(traineeList.size()-1).getCourseStartDate());
        received.add(traineeList.get(traineeList.size()-1).getCourseEndDate());

        System.out.println("Received array: " + received);

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(received.get(i), expected.get(i));
        }

    }

    @And("I send a PUT request")
    public void iSendAPUTRequest() {
        UpdateTraineeForm newTrainee = new UpdateTraineeForm("6203d4425a7a6c0b58931ba5", firstName, lastName, courseId, startDate);
        System.out.println("Generated trainee form: " + newTrainee.getJson());
        sendTraineePutRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());
    }


    @Then("The trainee should be updated to have the name {string} {string}, a course ID of {int}, a start date of {string}, and an end date of {string}")
    public void theTraineeShouldBeUpdatedToHaveTheNameACourseIDOfAStartDateOfAndAnEndDateOf(String arg0, String arg1, int arg2, String arg3, String arg4) {

        TraineeDTO trainee = (TraineeDTO) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan("6203d4425a7a6c0b58931ba5"), DTOEnum.TRAINEE);

        ArrayList<String> expected = new ArrayList<>();
        expected.add(arg0);
        expected.add(arg1);
        expected.add(String.valueOf(arg2));
        expected.add(arg3);
        expected.add(arg4);

        System.out.println("Expected Array: " + expected);

        ArrayList<String> received = new ArrayList<>();
        received.add(trainee.getFirstName());
        received.add(trainee.getLastName());
        received.add(String.valueOf(trainee.getCourseId()));
        received.add(trainee.getCourseStartDate());
        received.add(trainee.getCourseEndDate());

        System.out.println("Received array: " + received);

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(received.get(i), expected.get(i));
        }
    }

    @When("I search for first name {string}")
    public void iSearchForFirstName(String arg0) {
        firstName = arg0;
    }


    @Then("I should receive all trainees whose first name contains that")
    public void iShouldReceiveATraineeWithFirstNameOf() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().firstName(firstName).link(), DTOEnum.TRAINEE_LIST);
        for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
            Assertions.assertEquals(trainee.getFirstName(), firstName);
        }
    }

    @When("I search for last name {string}")
    public void iSearchForLastName(String arg0) {
        lastName = arg0;
    }

    @Then("I should receive all trainees whose last name contains that")
    public void iShouldReceiveATraineeWithTheLastNameOf() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().firstName(firstName).link(), DTOEnum.TRAINEE_LIST);
        for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
            Assertions.assertEquals(trainee.getLastName(), lastName);
        }
    }


    @When("I search before date {string}")
    public void iSearchBeforeDate(String arg0) {
        try {
            dateQuery = LocalDate.parse(arg0);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

    }

    @Then("I should receive all trainees that started before that date")
    public void iShouldReceiveAllTraineesThatStartedBeforeThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().BeforeAfter(UrlBuilder.TimeParameters.BEFORE).StartEnd(UrlBuilder.TimeParameters.START).date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);
        System.out.println("Trainees started before " + dateQuery + ":");

        for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
            Assertions.assertTrue(trainee.getStartDateAsDate().isBefore(dateQuery));
            System.out.print(trainee.getFullName() + ", ");
        }
    }
}
