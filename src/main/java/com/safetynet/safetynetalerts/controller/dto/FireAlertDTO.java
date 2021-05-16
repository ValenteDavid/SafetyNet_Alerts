package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class FireAlertDTO {

	private Set<PersonMedicalRecordDTO> personMedicalRecord;
	private int stationNumber;

	public FireAlertDTO() {
		super();
	}

	public FireAlertDTO(Set<PersonMedicalRecordDTO> setPersonMedicalRecord, int stationNumber) {
		super();
		this.personMedicalRecord = setPersonMedicalRecord;
		this.stationNumber = stationNumber;
	}

	public Set<PersonMedicalRecordDTO> getPersonMedicalRecord() {
		return personMedicalRecord;
	}

	public void setListPersonMedicalRecord(Set<PersonMedicalRecordDTO> setPersonMedicalRecord) {
		this.personMedicalRecord = setPersonMedicalRecord;
	}

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Override
	public String toString() {
		return "FireAlertDTO [setPersonMedicalRecord=" + personMedicalRecord + ", stationNumber=" + stationNumber
				+ "]";
	}

}
