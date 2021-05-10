package com.safetynet.safetynetalerts.controller.dto;

import com.safetynet.safetynetalerts.model.Person;

public class PersonFireStationAlertDTO {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	public PersonFireStationAlertDTO() {
		super();
	}

	public PersonFireStationAlertDTO(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PersonFireStationAlertDTO [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", phone=" + phone + "]";
	}

	public static PersonFireStationAlertDTO convertToDto(Person person) {
		return new PersonFireStationAlertDTO(person.getFirstName(), person.getLastName(), person.getAddress(),
				person.getPhone());
	}

}
