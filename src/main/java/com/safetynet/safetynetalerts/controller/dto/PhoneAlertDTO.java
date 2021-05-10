package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class PhoneAlertDTO {

	private Set<String> setNumbers;

	public PhoneAlertDTO() {
	}

	public PhoneAlertDTO(Set<String> setNumbers) {
		this.setNumbers = setNumbers;
	}

	public Set<String> getListNumbers() {
		return setNumbers;
	}

	public void setListNumbers(Set<String> setNumbers) {
		this.setNumbers = setNumbers;
	}

	@Override
	public String toString() {
		return "PhoneAlertDTO [setNumbers=" + setNumbers + "]";
	}

}
