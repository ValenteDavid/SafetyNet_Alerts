package com.safetynet.safetynetalerts.controller.dto;

import java.util.List;

public class FireStationAlertDTO {

	private List<PersonFireStationAlertDTO> listPersons;

	private int numberOfAdults;
	private int numberOfChildren;

	public List<PersonFireStationAlertDTO> getListPerson() {
		return listPersons;
	}

	public void setListPerson(List<PersonFireStationAlertDTO> listPersons) {
		this.listPersons = listPersons;
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
		return "FireStationAlertDTO [listPersons=" + listPersons + ", numberOfAdults=" + numberOfAdults
				+ ", numberOfChildren=" + numberOfChildren + "]";
	}

}
