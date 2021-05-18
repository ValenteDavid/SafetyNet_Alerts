package com.safetynet.safetynetalerts.controller.dto;

import java.util.Arrays;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class PersonMedicalRecordDTO {

	private String lastName;
	private String phone;
	private int age;

	private String[] medications;
	private String[] allergies;

	public PersonMedicalRecordDTO() {
		super();
	}

	public PersonMedicalRecordDTO(String lastName, String phone) {
		super();
		this.lastName = lastName;
		this.phone = phone;
	}

	public PersonMedicalRecordDTO(String lastName, String phone, int age, String[] medications, String[] allergies) {
		super();
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
		return "PersonMedicalRecordDTO [lastName=" + lastName + ", phone=" + phone + ", age=" + age + ", medications="
				+ Arrays.toString(medications) + ", allergies=" + Arrays.toString(allergies) + "]";
	}

	public static PersonMedicalRecordDTO convertToDto(Person person, MedicalRecord medicalRecord, int age) {
		return new PersonMedicalRecordDTO(person.getLastName(), person.getPhone(), age, medicalRecord.getMedications(),
				medicalRecord.getAllergies());
	}

}
