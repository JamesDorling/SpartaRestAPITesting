package org.sparta.DTOs;

import org.sparta.POJOs.CoursePojo;

public class CourseDTO extends CoursePojo implements DTO {

    public boolean idIsNotNull() {return getId() != null;}
    public boolean courseIdIsNotNull() {return getCourseId() != null;}
    public boolean courseNameIsNotNull() {return getCourseName() != null;}
    public boolean lengthIsNotNull() {return getLength() != null;}
    public boolean descriptionIsNotNull() {return getDescription() != null;}
    public boolean isActiveIsNotNull() {return isIsActive() != null;}
    public boolean linksIsNotNull() {return getLinks() != null;}
}
