package org.sparta.POJOs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.sparta.DTOs.TraineeDTO;

public class TraineePojoList{

	@JsonProperty("TraineePojoList")
	private List<TraineeDTO> traineePojoList;

	public List<TraineeDTO> getTraineeDTOList(){
		return traineePojoList;
	}

	@Override
 	public String toString(){
		return 
			"TraineePojoList{" + 
			"traineePojoList = '" + traineePojoList + '\'' + 
			"}";
		}
}