package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireAlertDTO {

	@JsonProperty("persons")
	private List<PersonMedicalRecordDTO> listPersonsMedicalRecord;
	private int stationNumber;

	public FireAlertDTO() {
		super();
	}

	public FireAlertDTO(List<PersonMedicalRecordDTO> listPersonsMedicalRecord, int stationNumber) {
		super();
		this.listPersonsMedicalRecord = listPersonsMedicalRecord;
		this.stationNumber = stationNumber;
	}

	public List<PersonMedicalRecordDTO> getListPersonsMedicalRecord() {
		return listPersonsMedicalRecord;
	}

	public void setListPersonsMedicalRecord(List<PersonMedicalRecordDTO> listPersonsMedicalRecord) {
		this.listPersonsMedicalRecord = listPersonsMedicalRecord;
	}

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Override
	public String toString() {
		return "FireAlertDTO [listPersonsMedicalRecord=" + listPersonsMedicalRecord + ", stationNumber=" + stationNumber
				+ "]";
	}

}
