package org.sparta.POJOs.CourseListPojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseList {

	@JsonProperty("_embedded")
	private CourseEmbedded embedded;

	public CourseEmbedded getEmbedded(){
		return embedded;
	}
}