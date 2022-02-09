package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraineePojo{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("courseEndDate")
	private String courseEndDate;

	@JsonProperty("courseStartDate")
	private String courseStartDate;

	@JsonProperty("courseId")
	private Integer courseId;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public Links getLinks(){
		return links;
	}

	public String getId(){
		return id;
	}

	public String getCourseEndDate(){
		return courseEndDate;
	}

	public String getCourseStartDate(){
		return courseStartDate;
	}

	public Integer getCourseId(){
		return courseId;
	}

	@Override
 	public String toString(){
		return 
			"TraineePojo{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",_links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",courseEndDate = '" + courseEndDate + '\'' + 
			",courseStartDate = '" + courseStartDate + '\'' + 
			",courseId = '" + courseId + '\'' + 
			"}";
		}
}