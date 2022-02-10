package org.sparta.crud_forms;

public class UpdateTraineeForm {
    private static String traineeJson;
    public UpdateTraineeForm(String id, String firstName, String lastName, Integer courseId, String startDate) {
        StringBuilder jsonSmithy = new StringBuilder("{\"id\":\"").append(id).append("\"");
        if(firstName != null) {
            jsonSmithy.append(",\"firstName\":\"").append(firstName).append("\"");
        }
        if(lastName != null) {
            jsonSmithy.append(",\"lastName\":\"").append(lastName).append("\"");
        }
        if(courseId != null) {
            jsonSmithy.append(",\"courseId\":\"").append(courseId).append("\"");
        }
        if(startDate != null) {
            jsonSmithy.append(",\"courseStartDate\":\"").append(startDate).append("\"");
        }
        traineeJson = jsonSmithy.append("}").toString();
    }
    //firstName or firstname

    public String getJson() {
        return traineeJson;
    }
}
