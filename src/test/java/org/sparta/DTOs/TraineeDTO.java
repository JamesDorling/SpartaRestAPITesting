package org.sparta.DTOs;

import org.sparta.POJOs.TraineePojo;

import java.time.LocalDate;

public class TraineeDTO extends TraineePojo implements DTO{
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

    public boolean noDataIsNull() {return firstNameIsNotNull() && lastNameIsNotNull() && startDateIsNotNull() && endDateIsNotNull() && idIsNotNull() && courseIdIsNotNull();}

    public boolean startIsBeforeEnd() {return startIsBefore(getEndDateAsDate());}
    public boolean endIsAfterStart() {return endIsAfter(getStartDateAsDate());}

    public String getCourseName() {
        return switch (getCourseId()) {
            case 1 -> "java";
            case 2 -> "c#";
            case 3 -> "data";
            case 4 -> "devops";
            case 5 -> "cyber security";
            case 6 -> "business";
            default -> throw new IllegalStateException("Unexpected value (course ID): " + getCourseId());
        };
    }

    public String getTraineeAsJson() {
        return "{\"id\":\"" + getId() +"\"," +
                "\"firstname\":\""+ getFirstName() +"\"," +
                "\"lastName\":\""+ getLastName() +"\"," +
                "\"courseId\":\""+ getCourseId() +"\"," +
                "\"courseStartDate\":\""+ getCourseStartDate() +"\"," +
                "\"courseEndDate\":\""+ getCourseEndDate() +"\"}";
    }
}
