package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class FloodAlertDTO {

	private Set<PersonMedicalRecordDTO> setPerson;
	private String address;

	public FloodAlertDTO() {
		super();
	}

	public FloodAlertDTO(Set<PersonMedicalRecordDTO> setPerson, String address) {
		super();
		this.setPerson = setPerson;
		this.address = address;
	}

	public Set<PersonMedicalRecordDTO> getSetPerson() {
		return setPerson;
	}

	public void setListPerson(Set<PersonMedicalRecordDTO> setPerson) {
		this.setPerson = setPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "FloodAlertDTO [setPerson=" + setPerson + ", address=" + address + "]";
	}

}
