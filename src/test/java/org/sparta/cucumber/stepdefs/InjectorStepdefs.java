package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.framework.Injector;

public class InjectorStepdefs {

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
        Assertions.assertEquals(firstName, trainee.getFirstName());
    }
}
