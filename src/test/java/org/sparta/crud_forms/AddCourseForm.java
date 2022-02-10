package org.sparta.crud_forms;

public class AddCourseForm {
    private static String courseJson;
    public AddCourseForm(int courseId, String courseName, int length, String description, boolean active) {
        courseJson = "{\"courseId\":\""+ courseId +"\"," +
                "\"courseName\":\""+ courseName +"\"," +
                "\"length\":"+ length +"," +
                "\"description\":\""+ description +"\"}" +
                "\"active\":\""+ active +"\"}";
    }

    public String getJson() {
        return courseJson;
    }
}

