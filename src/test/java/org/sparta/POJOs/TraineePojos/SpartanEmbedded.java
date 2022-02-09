package org.sparta.POJOs.TraineePojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.sparta.DTOs.TraineeDTO;

public class SpartanEmbedded {

	@JsonProperty("spartanEntityList")
	private List<TraineeDTO> spartanEntityList;

	public List<TraineeDTO> getSpartanEntityList(){
		return spartanEntityList;
	}

	@Override
 	public String toString(){
		return 
			"Embedded{" + 
			"spartanEntityList = '" + spartanEntityList + '\'' + 
			"}";
		}
}