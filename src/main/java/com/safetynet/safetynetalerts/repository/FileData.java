package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class FileData {
	List<Person> persons;
	List<FireStation> fireStations;
	List<MedicalRecord> medicalRecords;

	public FileData(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords) {
		this.persons = persons;
		this.fireStations = fireStations;
		this.medicalRecords = medicalRecords;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<FireStation> getFireStations() {
		return fireStations;
	}

	public void setFireStations(List<FireStation> fireStations) {
		this.fireStations = fireStations;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

}
