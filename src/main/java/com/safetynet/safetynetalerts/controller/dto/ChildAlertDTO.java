package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildAlertDTO {

	@JsonProperty("children")
	private List<PersonChildAlertDTO> listChildren;

	public ChildAlertDTO() {
		super();
	}

	public ChildAlertDTO(List<PersonChildAlertDTO> listChildren) {
		super();
		this.listChildren = listChildren;
	}

	public List<PersonChildAlertDTO> getListChildren() {
		return listChildren;
	}

	public void setListChildren(List<PersonChildAlertDTO> listChildren) {
		this.listChildren = listChildren;
	}

	

}
