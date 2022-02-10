package org.sparta.crud_forms;

public class AddCourseForm {
    private static String courseJson;
    public AddCourseForm(String courseName, int length, String description) {
        courseJson = "{\"courseName\":\""+ courseName +"\"," +
                "\"length\":"+ length +"," +
                "\"description\":\""+ description +"\"}";
    }

    public String getJson() {
        return courseJson;
    }
}

