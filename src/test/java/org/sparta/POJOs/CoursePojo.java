package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sparta.POJOs.CourseListPojos.HATAEOSExtension.CourseHATEOASLinks;

public class CoursePojo{

	@JsonProperty("id")
	private String id;

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

	@JsonProperty("_links")
	private CourseHATEOASLinks links;

	public String getId() {
		return id;
	}

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

	public CourseHATEOASLinks getLinks(){return links;}

	@Override
 	public String toString(){
		return 
			"CoursePojo{" +
					"id = '" + id + '\'' +
					"course_id = '" + courseId + '\'' +
			",course_name = '" + courseName + '\'' + 
			",length = '" + length + '\'' + 
			",description = '" + description + '\'' +
			",isActive = '" + active + '\'' +
			"}";
		}
}