package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpartanHATEOASLinks {

	@JsonProperty("course")
	private SpartanCourseLink course;

	@JsonProperty("self")
	private Self self;

	public SpartanCourseLink getCourse(){
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