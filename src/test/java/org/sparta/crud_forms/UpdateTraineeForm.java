package org.sparta.crud_forms;

public class UpdateTraineeForm {
    private static String traineeJson;
    public UpdateTraineeForm(String id, String firstName, String lastName, Integer courseId, String startDate) {
        traineeJson = "{\"id\":\"" + id +"\"," +
                "\"firstName\":\""+ firstName +"\"," +
                "\"lastName\":\""+ lastName +"\"," +
                "\"courseId\":"+ courseId +"," +
                "\"courseStartDate\":\""+ startDate +"\"}";
    }
    //firstName or firstname

    public String getJson() {
        return traineeJson;
    }
}
