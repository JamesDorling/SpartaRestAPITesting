package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraineePojo{

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("course_id")
	private Integer courseId;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("_id")
	private Id id;

	@JsonProperty("courseEndDate")
	private String courseEndDate;

	@JsonProperty("courseStartDate")
	private String courseStartDate;

	public String getLastName(){
		return lastName;
	}

	public Integer getCourseId(){
		return courseId;
	}

	public String getFirstName(){
		return firstname;
	}

	public Id getId(){
		return id;
	}

	public String getCourseEndDate(){
		return courseEndDate;
	}

	public String getCourseStartDate(){
		return courseStartDate;
	}

	@Override
 	public String toString(){
		return 
			"TraineePojo{" + 
			"lastName = '" + lastName + '\'' + 
			",course_id = '" + courseId + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",_id = '" + id + '\'' + 
			",courseEndDate = '" + courseEndDate + '\'' + 
			",courseStartDate = '" + courseStartDate + '\'' + 
			"}";
		}
}