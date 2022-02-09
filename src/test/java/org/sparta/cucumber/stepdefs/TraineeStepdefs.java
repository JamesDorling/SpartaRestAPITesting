package org.sparta.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.DTOs.TraineeDTOList;
import org.sparta.crud_forms.TraineeForm;
import org.sparta.framework.connection.ConnectionManager;

import java.util.List;

import static org.sparta.framework.Injector.injectDTO;
import static org.sparta.framework.connection.ConnectionManager.makeUrl;
import static org.sparta.framework.connection.ConnectionManager.sendTraineePostRequest;

public class TraineeStepdefs {

    static TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
    static List<TraineeDTO> traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();

    private String firstName;
    private String lastName;
    private int courseId;
    private String startDate;


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

    }

    public static void main(String[] args) {
        System.out.println(traineeList.get(traineeList.size()-1).getTraineeAsJson());
        TraineeForm newTrainee = new TraineeForm("Callum", "DK", 1, "2022-01-01");
        System.out.println(newTrainee.getJson());

        //ConnectionManager.sendTraineePostRequest(newTrainee.getJson(), ConnectionManager.makeUrl().spartan().link()).body();
        System.out.println(sendTraineePostRequest(newTrainee.getJson(), makeUrl().spartan().link()).body());


        TraineeDTOList traineeDTOList = (TraineeDTOList) injectDTO(ConnectionManager.makeUrl().spartan().link(), DTOEnum.TRAINEE_LIST);
        System.out.println(newTrainee.getJson());
        traineeList = traineeDTOList.getEmbedded().getSpartanEntityList();
        System.out.println(traineeList.get(traineeList.size()-1).getTraineeAsJson());
        Assertions.assertEquals("james", traineeList.get(traineeList.size()-1).getFirstName());

    }
}
