package org.sparta.crud_forms;

public class UpdateCourseForm {
    private static String courseJson;
    public UpdateCourseForm(String id, Integer courseId, String courseName, Integer length, String description, boolean active) {
        StringBuilder jsonSmithy = new StringBuilder("{\"id\":\"").append(id).append("\"");
        if(courseId != null) {
            jsonSmithy.append(",\"courseId\":\"").append(courseId).append("\"");
        }
        if(courseName != null) {
            jsonSmithy.append(",\"courseName\":\"").append(courseName).append("\"");
        }
        if(description != null) {
            jsonSmithy.append(",\"courseId\":\"").append(description).append("\"");
        }
        if(length != null) {
            jsonSmithy.append(",\"length\":\"").append(length).append("\"");
        }
        if(active) {
            jsonSmithy.append(",\"active\":\"").append(true).append("\"");
        }
        courseJson = jsonSmithy.append("}").toString();
    }

    public String getJson() {
        return courseJson;
    }
}
