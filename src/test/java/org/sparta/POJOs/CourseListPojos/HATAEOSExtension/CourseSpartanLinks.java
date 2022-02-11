package org.sparta.POJOs.CourseListPojos.HATAEOSExtension;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseSpartanLinks {

	@JsonProperty("href")
	private String href;

	public String getHref(){
		return href;
	}
}