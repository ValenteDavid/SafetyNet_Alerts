package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonInfoAlertDTO {
	
	@JsonProperty("persons")
	private List<PersonInfoDTO> listPersonInfo;
	
	public PersonInfoAlertDTO() {
		super();
	}

	public PersonInfoAlertDTO(List<PersonInfoDTO> listPersonInfo) {
		super();
		this.listPersonInfo = listPersonInfo;
	}

	public List<PersonInfoDTO> getListPersonInfo() {
		return listPersonInfo;
	}

	public void setListPersonInfo(List<PersonInfoDTO> listPersonInfo) {
		this.listPersonInfo = listPersonInfo;
	}

	@Override
	public String toString() {
		return "PersonInfoAlertDTO [listPersonInfo=" + listPersonInfo + "]";
	}

}
