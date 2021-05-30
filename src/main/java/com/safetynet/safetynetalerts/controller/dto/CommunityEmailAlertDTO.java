package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityEmailAlertDTO {

	@JsonProperty("email")
	private List<String> listEmail;

	public CommunityEmailAlertDTO() {
		super();
	}

	public CommunityEmailAlertDTO(List<String> listEmail) {
		super();
		this.listEmail = listEmail;
	}

	public List<String> getListEmail() {
		return listEmail;
	}

	public void setListEmail(List<String> listEmail) {
		this.listEmail = listEmail;
	}

	@Override
	public String toString() {
		return "CommunityEmailAlertDTO [listEmail=" + listEmail + "]";
	}

}
