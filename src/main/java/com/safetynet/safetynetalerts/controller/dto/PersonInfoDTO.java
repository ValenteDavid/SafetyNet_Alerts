package com.safetynet.safetynetalerts.controller.dto;

import java.util.Arrays;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class PersonInfoDTO {
	
	private String lastName;
	private String address;
	private int age;
	private String email;

	private String[] medications;
	private String[] allergies;

	public PersonInfoDTO() {
		super();
	}

	public PersonInfoDTO(String lastName, String address, int age, String email, String[] medications,
			String[] allergies) {
		super();
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public static PersonInfoDTO convertToDto(Person person, MedicalRecord medicalRecord, int age) {
		return new PersonInfoDTO(
				person.getLastName(),
				person.getAddress() + " " + person.getZip() + " " + person.getCity(),
				age,
				person.getEmail(),
				medicalRecord.getMedications(),
				medicalRecord.getAllergies());
	}

	@Override
	public String toString() {
		return "PersonInfoAlertDTO [lastName=" + lastName + ", address=" + address + ", age=" + age + ", email=" + email
				+ ", medications=" + Arrays.toString(medications) + ", allergies=" + Arrays.toString(allergies) + "]";
	}
}
