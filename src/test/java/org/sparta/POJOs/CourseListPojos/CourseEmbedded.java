package org.sparta.POJOs.CourseListPojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.sparta.DTOs.CourseDTO;
import org.sparta.POJOs.CoursePojo;

public class CourseEmbedded {

	@JsonProperty("courseEntityList")
	private List<CourseDTO> courseEntityList;

	public List<CourseDTO> getCourseDTOList(){
		return courseEntityList;
	}
}