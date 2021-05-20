package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityEmailAlertDTO {

	@JsonProperty("email")
	private Set<String> listEmail;

	public CommunityEmailAlertDTO() {
		super();
	}

	public CommunityEmailAlertDTO(Set<String> listEmail) {
		super();
		this.listEmail = listEmail;
	}

	public Set<String> getListEmail() {
		return listEmail;
	}

	public void setListEmail(Set<String> listEmail) {
		this.listEmail = listEmail;
	}

	@Override
	public String toString() {
		return "CommunityEmailAlertDTO [listEmail=" + listEmail + "]";
	}

}
