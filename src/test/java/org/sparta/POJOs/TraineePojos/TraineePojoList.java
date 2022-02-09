package org.sparta.POJOs.TraineePojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraineePojoList{

	@JsonProperty("_embedded")
	private SpartanEmbedded embedded;

	public SpartanEmbedded getEmbedded(){
		return embedded;
	}

	@Override
 	public String toString(){
		return 
			"TraineePojoList{" + 
			"_embedded = '" + embedded + '\'' + 
			"}";
		}
}