package org.sparta.POJOs.LinkPojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpartanCourseLink {

	@JsonProperty("href")
	private String href;

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"Course{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}