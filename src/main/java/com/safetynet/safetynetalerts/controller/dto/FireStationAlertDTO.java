package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class FireStationAlertDTO {

	private Set<PersonFireStationAlertDTO> setPerson;
	
	private int numberOfAdults;
	private int numberOfChildren;
	
	public Set<PersonFireStationAlertDTO> getListPerson() {
		return setPerson;
	}
	public void setListPerson(Set<PersonFireStationAlertDTO> setPerson) {
		this.setPerson = setPerson;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	@Override
	public String toString() {
		return "FireStationAlertDTO [setPerson=" + setPerson + ", numberOfAdults=" + numberOfAdults
				+ ", numberOfChildren=" + numberOfChildren + "]";
	}
	
}
