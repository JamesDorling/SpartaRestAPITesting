package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoursePojo{

	@JsonProperty("courseId")
	private Integer courseId;

	@JsonProperty("courseName")
	private String courseName;

	@JsonProperty("length")
	private Integer length;

	@JsonProperty("description")
	private String description;

	@JsonProperty("active")
	private Boolean active;

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

	public Boolean isIsActive(){
		return active;
	}

	@Override
 	public String toString(){
		return 
			"CoursePojo{" + 
			"course_id = '" + courseId + '\'' + 
			",course_name = '" + courseName + '\'' + 
			",length = '" + length + '\'' + 
			",description = '" + description + '\'' +
			",isActive = '" + active + '\'' +
			"}";
		}
}