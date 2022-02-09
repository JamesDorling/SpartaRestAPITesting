package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links{

	@JsonProperty("course")
	private Course course;

	@JsonProperty("self")
	private Self self;

	public Course getCourse(){
		return course;
	}

	public Self getSelf(){
		return self;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"course = '" + course + '\'' + 
			",self = '" + self + '\'' + 
			"}";
		}
}