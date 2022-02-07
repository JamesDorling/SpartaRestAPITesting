package org.example.DTOs;

import org.example.POJOs.TraineePojo;

import java.time.LocalDateTime;

public class TraineeDTO extends TraineePojo {
    public String getFullName() {
        return getFirstname() + " " + getLastName();
    }

    public LocalDateTime getStartDateAsDate() {
        return LocalDateTime.parse(getCourseStartDate());
    }
}
