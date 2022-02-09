package org.sparta.POJOs.CourseListPojos.HATAEOSExtension;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseHATEOASLinks {

	@JsonProperty("Spartan")
	private List<CourseSpartanLinks> spartan;

	public List<CourseSpartanLinks> getSpartanList(){
		return spartan;
	}
}