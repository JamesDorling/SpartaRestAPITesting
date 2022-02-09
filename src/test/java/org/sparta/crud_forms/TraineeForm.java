package org.sparta.crud_forms;

public class TraineeForm {
    private static String traineeJson;
    public TraineeForm(String firstName, String lastName, Integer courseId, String startDate) {
        traineeJson = "{\"firstName\":\""+ firstName +"\"," +
                "\"lastName\":\""+ lastName +"\"," +
                "\"courseId\":"+ courseId +"," +
                "\"courseStartDate\":\""+ startDate +"\"}";
    }
    //firstName or firstname

    public String getJson() {
        return traineeJson;
    }
}
