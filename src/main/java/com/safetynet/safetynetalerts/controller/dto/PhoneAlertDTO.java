package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

public class PhoneAlertDTO {

	private List<String> listNumbers;

	public PhoneAlertDTO() {
	}

	public PhoneAlertDTO(List<String> listNumbers) {
		super();
		this.listNumbers = listNumbers;
	}

	public List<String> getListNumbers() {
		return listNumbers;
	}

	public void setListNumbers(List<String> listNumbers) {
		this.listNumbers = listNumbers;
	}

	@Override
	public String toString() {
		return "PhoneAlertDTO []";
	}

}
