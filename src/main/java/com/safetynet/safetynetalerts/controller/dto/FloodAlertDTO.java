package com.safetynet.safetynetalerts.controller.dto;

import java.util.Map;

public class FloodAlertDTO {

	private Map<String,PersonMedicalRecordDTO> mapAddressPerson;

	public FloodAlertDTO() {
		super();
	}

	public FloodAlertDTO(Map<String, PersonMedicalRecordDTO> mapAddressPerson) {
		super();
		this.mapAddressPerson = mapAddressPerson;
	}

	public Map<String, PersonMedicalRecordDTO> getMapAddressPerson() {
		return mapAddressPerson;
	}

	public void setMapAddressPerson(Map<String, PersonMedicalRecordDTO> mapAddressPerson) {
		this.mapAddressPerson = mapAddressPerson;
	}

	@Override
	public String toString() {
		return "FloodAlertDTO [mapAddressPerson=" + mapAddressPerson + "]";
	}

}
