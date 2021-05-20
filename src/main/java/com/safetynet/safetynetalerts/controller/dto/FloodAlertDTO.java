package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloodAlertDTO {

	@JsonProperty("addressesPersons")
	private Map<String,List<PersonMedicalRecordDTO>> mapAddressPerson;

	public FloodAlertDTO() {
		super();
	}

	public FloodAlertDTO(Map<String, List<PersonMedicalRecordDTO>> mapAddressPerson) {
		super();
		this.mapAddressPerson = mapAddressPerson;
	}

	public Map<String, List<PersonMedicalRecordDTO>> getMapAddressPerson() {
		return mapAddressPerson;
	}

	public void setMapAddressPerson(Map<String, List<PersonMedicalRecordDTO>> mapAddressPerson) {
		this.mapAddressPerson = mapAddressPerson;
	}

	@Override
	public String toString() {
		return "FloodAlertDTO [mapAddressPerson=" + mapAddressPerson + "]";
	}

}
