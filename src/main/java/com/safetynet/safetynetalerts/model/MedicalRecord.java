package com.safetynet.safetynetalerts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class MedicalRecord {

	private String firstName;
	private String lastName;
	@Past
	private Date birthdate;
	private String[] medications;
	private String[] allergies;

	public MedicalRecord() {
		super();
	}

	public MedicalRecord(String firstName, String lastName, Date birthdate, String[] medications,
			String[] allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonFormat(pattern="dd/MM/yyyy")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBirthdate(String birthdate) {
		try {
			this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String[] getMedications() {
		return medications;
	}

	public void setMedications(String[] medications) {
		this.medications = medications;
	}

	public String[] getAllergies() {
		return allergies;
	}

	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", medications=" + Arrays.toString(medications) + ", allergies=" + Arrays.toString(allergies) + "]";
	}

}
