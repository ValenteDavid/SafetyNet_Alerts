package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class FireAlertDTO {

	private Set<PersonMedicalRecordDTO> setPersonMedicalRecord;
	private int stationNumber;

	public FireAlertDTO() {
		super();
	}

	public FireAlertDTO(Set<PersonMedicalRecordDTO> setPersonMedicalRecord, int stationNumber) {
		super();
		this.setPersonMedicalRecord = setPersonMedicalRecord;
		this.stationNumber = stationNumber;
	}

	public Set<PersonMedicalRecordDTO> getSetPersonMedicalRecord() {
		return setPersonMedicalRecord;
	}

	public void setListPersonMedicalRecord(Set<PersonMedicalRecordDTO> setPersonMedicalRecord) {
		this.setPersonMedicalRecord = setPersonMedicalRecord;
	}

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Override
	public String toString() {
		return "FireAlertDTO [setPersonMedicalRecord=" + setPersonMedicalRecord + ", stationNumber=" + stationNumber
				+ "]";
	}

}
