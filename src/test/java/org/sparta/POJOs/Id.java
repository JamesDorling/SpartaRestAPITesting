package org.sparta.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id{

	@JsonProperty("$oid")
	private String oid;

	public String getOid(){
		return oid;
	}

	@Override
 	public String toString(){
		return 
			"Id{" + 
			"$oid = '" + oid + '\'' + 
			"}";
		}

	public Id() {
	}

	public Id(String oid) {
		this.oid = oid;
	}
}