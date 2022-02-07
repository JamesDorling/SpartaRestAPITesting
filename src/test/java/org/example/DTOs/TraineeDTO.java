package org.example.DTOs;

import org.example.POJOs.TraineePojo;

import java.time.LocalDate;

public class TraineeDTO extends TraineePojo {
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public LocalDate getStartDateAsDate() {
        return LocalDate.parse(getCourseStartDate());
    }
    public LocalDate getEndDateAsDate() {
        return LocalDate.parse(getCourseEndDate());
    }
}
