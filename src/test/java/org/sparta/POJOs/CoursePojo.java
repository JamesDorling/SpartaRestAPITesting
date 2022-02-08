package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoursePojo{

	@JsonProperty("course_id")
	private Integer courseId;

	@JsonProperty("course_name")
	private String courseName;

	@JsonProperty("length")
	private Integer length;

	@JsonProperty("description")
	private String description;

	@JsonProperty("_id")
	private Id id;

	@JsonProperty("isActive")
	private Boolean isActive;

	public Integer getCourseId(){
		return courseId;
	}

	public String getCourseName(){
		return courseName;
	}

	public Integer getLength(){
		return length;
	}

	public String getDescription(){
		return description;
	}

	public Id getId(){
		return id;
	}

	public Boolean isIsActive(){
		return isActive;
	}

	@Override
 	public String toString(){
		return 
			"CoursePojo{" + 
			"course_id = '" + courseId + '\'' + 
			",course_name = '" + courseName + '\'' + 
			",length = '" + length + '\'' + 
			",description = '" + description + '\'' + 
			",_id = '" + id + '\'' + 
			",isActive = '" + isActive + '\'' + 
			"}";
		}
}