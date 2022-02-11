package org.sparta.POJOs.LinkPojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Self{

	@JsonProperty("href")
	private String href;

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"Self{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}