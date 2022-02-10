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
import org.sparta.framework.connection.UrlBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.*;

public class TraineeStepdefs {

    private static final TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
    private static List<TraineeDTO> traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();

    private String id;
    private String firstName;
    private String lastName;
    private int courseId;
    private String startDate;
    private String endDate;

    private LocalDate dateQuery;

    @When("I enter ID of {string}")
    public void iEnterIDOf(String arg0) {
        id = arg0;
    }

    @When("I enter first name of {string}")
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

        System.out.println("Expected trainee: " + expected);

        ArrayList<String> received = new ArrayList<>();

        for (TraineeDTO trainee : traineeList) {
            if (trainee.getFirstName().equals(firstName)){
                received.add(traineeList.get(traineeList.size()-1).getFirstName());
                received.add(traineeList.get(traineeList.size()-1).getLastName());
                received.add(String.valueOf(traineeList.get(traineeList.size()-1).getCourseId()));
                received.add(traineeList.get(traineeList.size()-1).getCourseStartDate());
                received.add(traineeList.get(traineeList.size()-1).getCourseEndDate());
            }
        }


        System.out.println("Received trainee: " + received);

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(received.get(i), expected.get(i));
        }

    }

    @And("I send a PUT request")
    public void iSendAPUTRequest() {
        UpdateTraineeForm newTrainee = new UpdateTraineeForm(id, firstName, lastName, courseId, startDate);
        System.out.println("Generated trainee form: " + newTrainee.getJson());
        sendTraineePutRequest(newTrainee.getJson(), makeUrl().getSpartanWithKey());
    }


    @Then("The trainee should be updated to have the name {string} {string}, a course ID of {int}, a start date of {string}, and an end date of {string}")
    public void theTraineeShouldBeUpdatedToHaveTheNameACourseIDOfAStartDateOfAndAnEndDateOf(String arg0, String arg1, int arg2, String arg3, String arg4) {

        TraineeDTO trainee = (TraineeDTO) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan(id), DTOEnum.TRAINEE);

        ArrayList<String> expected = new ArrayList<>();
        expected.add(arg0);
        expected.add(arg1);
        expected.add(String.valueOf(arg2));
        expected.add(arg3);
        expected.add(arg4);

        System.out.println("Expected trainee: " + expected);

        ArrayList<String> received = new ArrayList<>();
        received.add(trainee.getFirstName());
        received.add(trainee.getLastName());
        received.add(String.valueOf(trainee.getCourseId()));
        received.add(trainee.getCourseStartDate());
        received.add(trainee.getCourseEndDate());

        System.out.println("Received trainee: " + received);

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

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("There are no trainees whose first name contains " + "\"" + firstName + "\".");
        } else {
            System.out.println("Found " + traineeDTOList.getEmbedded().getSpartanEntityList().size() + " trainees:");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getFirstName().contains(firstName));
                System.out.print(trainee.getFullName() + ", ");
            }
        }
    }

    @When("I search for last name {string}")
    public void iSearchForLastName(String arg0) {
        lastName = arg0;
    }

    @Then("I should receive all trainees whose last name contains that")
    public void iShouldReceiveATraineeWithTheLastNameOf() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().lastName(lastName).link(), DTOEnum.TRAINEE_LIST);
        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("There are no trainees whose last name contains " + "\"" + lastName + "\"." );
        } else {
            System.out.println("Found " + traineeDTOList.getEmbedded().getSpartanEntityList().size() + " trainees:");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getLastName().contains(lastName));
                System.out.print(trainee.getFullName() + ", ");
            }
        }
    }

    @Then("I should receive all trainees whose first and last names contain those")
    public void iShouldReceiveAllTraineesWhoseFirstAndLastNamesContainThose() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().firstName(firstName).lastName(lastName).link(), DTOEnum.TRAINEE_LIST);
        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("There are no trainees whose name contains " + "\"" + firstName + "\"" + " and last name contains " + "\"" + lastName + "\".");
        } else {
            System.out.println("Found " + traineeDTOList.getEmbedded().getSpartanEntityList().size() + " trainees:");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getFirstName().contains(firstName) && trainee.getLastName().contains(lastName));
                System.out.print(trainee.getFullName() + ", ");
            }
        }
    }


    @When("I search: started before {string}")
    public void iSearchBeforeDate(String arg0) {
        parseDate(arg0);

    }

    @Then("I should receive all trainees that started before that date")
    public void iShouldReceiveAllTraineesThatStartedBeforeThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.BEFORE)
                .StartEnd(UrlBuilder.TimeParameters.START)
                .date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees started before " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees started before " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getStartDateAsDate().isBefore(dateQuery));
                System.out.print(trainee.getFullName() +"(" + trainee.getCourseStartDate() + "), ");
            }
        }
    }

    @When("I search: started after {string}")
    public void iSearchAfterDate(String arg0) {
        parseDate(arg0);
    }

    @Then("I should receive all trainees that started after that date")
    public void iShouldReceiveAllTraineesThatStartedAfterThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.AFTER)
                .StartEnd(UrlBuilder.TimeParameters.START)
                .date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees started after " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees started after " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getStartDateAsDate().isAfter(dateQuery));
                System.out.print(trainee.getFullName() +"(" + trainee.getCourseStartDate() + "), ");
            }
        }
    }

    @When("I search: started on {string}")
    public void iSearchForDateString(String arg0) {
        parseDate(arg0);
    }


    @Then("I should receive all trainees that started on that date")
    public void iShouldReceiveAllTraineesThatStartedOnThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.NOW)
                .StartEnd(UrlBuilder.TimeParameters.START)
                .date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees started on " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees started on " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertEquals(trainee.getStartDateAsDate(), dateQuery);
                System.out.print(trainee.getFullName() +"(" + trainee.getCourseStartDate() + "), ");
            }
        }
    }

    @When("I search: finished before {string}")
    public void iSearchFinishedBeforeDate(String arg0) {
        parseDate(arg0);
    }

    @Then("I should receive all trainees that finished before that date")
    public void iShouldReceiveAllTraineesThatFinishedBeforeThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.BEFORE)
                .StartEnd(UrlBuilder.TimeParameters.END).date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees finished before " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees finished before " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getEndDateAsDate().isBefore(dateQuery));
                System.out.print(trainee.getFullName() + "(" + trainee.getCourseEndDate() + ")" + ", ");
            }
        }
    }

    @When("I search: finished after {string}")
    public void iSearchFinishedAfter(String arg0) {
        parseDate(arg0);
    }

    @Then("I should receive all trainees that finished after that date")
    public void iShouldReceiveAllTraineesThatFinishedAfterThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.AFTER)
                .StartEnd(UrlBuilder.TimeParameters.END)
                .date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees finished after " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees finished after " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertTrue(trainee.getEndDateAsDate().isAfter(dateQuery));
                System.out.print(trainee.getFullName() + "(" + trainee.getCourseEndDate() + ")" + ", ");
            }
        }
    }

    @When("I search: finished on {string}")
    public void iSearchFinishedAfterString(String arg0) {
        parseDate(arg0);
    }

    @Then("I should receive all trainees that finished on that date")
    public void iShouldReceiveAllTraineesThatFinishedOnThatDate() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan()
                .BeforeAfter(UrlBuilder.TimeParameters.NOW)
                .StartEnd(UrlBuilder.TimeParameters.END)
                .date(dateQuery.toString()).link(), DTOEnum.TRAINEE_LIST);

        if (traineeDTOList.getEmbedded() == null) {
            System.out.println("0 trainees finished on " + dateQuery + ".");
        } else {
            System.out.println(traineeDTOList.getEmbedded().getSpartanEntityList().size() +  " trainees finished on " + dateQuery + ":");
            for (TraineeDTO trainee : traineeDTOList.getEmbedded().getSpartanEntityList()) {
                Assertions.assertEquals(trainee.getEndDateAsDate(), dateQuery);
                System.out.print(trainee.getFullName() + "(" + trainee.getCourseEndDate() + ")" + ", ");
            }
        }
    }

    @When("I search for id {string}")
    public void iSearchForId(String arg0) {
        id = arg0;
    }

    @Then("I should receive the trainee that matches that id")
    public void iShouldReceiveTheTraineeThatMatchesThatId() {
        TraineeDTO trainee = (TraineeDTO) injectDTO(ConnectionManager.makeUrl().getSpecificSpartan(id), DTOEnum.TRAINEE);
        Assertions.assertEquals(trainee.getId(), id);
        System.out.println("Trainee found: " + trainee.getFullName() + "(" + trainee.getId() + ")" );

    }

    private void parseDate(String arg0) {
        try {
            dateQuery = LocalDate.parse(arg0);
        } catch (DateTimeParseException e) {
            System.err.println("Incorrect date format. Please enter a date in the form \"yyyy-MM-dd\"");
        }
    }
}
