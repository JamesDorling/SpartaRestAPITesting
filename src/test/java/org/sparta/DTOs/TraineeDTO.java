package org.sparta.DTOs;

import org.sparta.POJOs.TraineePojo;

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

    public boolean startIsBefore(LocalDate date) {return date.isAfter(getStartDateAsDate());}
    public boolean startIsAfter(LocalDate date) {return date.isBefore(getStartDateAsDate());}
    public boolean endIsBefore(LocalDate date) {return date.isAfter(getEndDateAsDate());}
    public boolean endIsAfter(LocalDate date) {return date.isBefore(getEndDateAsDate());}

    public boolean firstNameIsNotNull() {return getFirstName() != null;}
    public boolean lastNameIsNotNull() {return getLastName() != null;}
    public boolean startDateIsNotNull() {return getCourseStartDate() != null;}
    public boolean endDateIsNotNull() {return getCourseEndDate() != null;}
    public boolean idIsNotNull() {return getId() != null;}
    public boolean courseIdIsNotNull() {return getCourseId() != null;}

}
