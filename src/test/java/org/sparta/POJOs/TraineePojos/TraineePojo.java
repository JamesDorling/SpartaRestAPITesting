package org.sparta.POJOs.TraineePojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraineePojo{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("_links")
	private SpartanHATEOASLinks spartanHATEOASLinks;

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

	public SpartanHATEOASLinks getLinks(){
		return spartanHATEOASLinks;
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
			",_links = '" + spartanHATEOASLinks + '\'' +
			",id = '" + id + '\'' + 
			",courseEndDate = '" + courseEndDate + '\'' + 
			",courseStartDate = '" + courseStartDate + '\'' + 
			",courseId = '" + courseId + '\'' + 
			"}";
		}
}