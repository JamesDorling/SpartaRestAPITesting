package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.framework.Injector;
import org.sparta.framework.connection.ConnectionManager;

import java.util.List;

import static org.sparta.framework.Injector.injectDTO;

public class InjectorStepdefs {

    List<TraineeDTO> traineeList;

    TraineeDTO trainee;

    @Given("Data has been injected from a file")
    public void dataHasBeenInjectedFromAFile() {
        trainee = (TraineeDTO) Injector.injectDTOFromFile("src/test/resources/json/IndividualTrainee.json", DTOEnum.TRAINEE);
    }

    @When("I check trainee first name")
    public void iCheckTraineeFirstName() {

    }

    @Then("First name should not be null")
    public void firstNameShouldNotBeNull() {
        Assertions.assertTrue(trainee.firstNameIsNotNull());
    }

    @Then("First name should be {string}")
    public void firstNameShouldBe(String firstName) {
        Assertions.assertEquals(firstName, traineeList.get(0).getFirstName());
    }

    @Given("I am connected to the database")
    public void iAmConnectedToTheDatabase() {
        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
        traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();
    }

    @When("I check the first trainee's name")
    public void iCheckTheFirstTraineeSName() {
        System.out.println("First trainee is: " + traineeList.get(0).getFirstName());
    }
}
