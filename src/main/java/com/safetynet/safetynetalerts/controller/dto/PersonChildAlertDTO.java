package com.safetynet.safetynetalerts.controller.dto;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class PersonChildAlertDTO {

	private String firstName;
	private String lastName;
	private int age;

	public PersonChildAlertDTO() {
		super();
	}

	public PersonChildAlertDTO(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonChildAlertDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}

	public static PersonChildAlertDTO convertToDto(Person person, int age) {
		return new PersonChildAlertDTO(person.getFirstName(), person.getLastName(), age);
	}

	public static PersonChildAlertDTO convertToDto(MedicalRecord medicalRecord, int age) {
		return new PersonChildAlertDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), age);
	}

}
