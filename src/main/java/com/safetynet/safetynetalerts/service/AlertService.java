package com.safetynet.safetynetalerts.service;

import java.util.Date;
import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public interface AlertService {

	public List<Person> listPersonByStationNumber(int stationNumber);

	public List<Person> listPersonByAddress(String address);

	public List<Person> listChildren(List<Person> listPersons);

	public List<Person> listAdult(List<Person> listPersons);

	public int ageOfPersonByBirthdate(Date birthdate);

	public int ageOfPersonByPerson(Person person);

	public int numberOfChildren(List<Person> listPersons);

	public int numberOfAdult(List<Person> listPersons);

	public List<String> listEmail(List<Person> listPersons);

	public List<String> listPersonPhoneByStationNumber(int stationNumber);

	public Integer findStationNumberByAddress(String Address);

	public MedicalRecord listMedicalRecordByFirstNameANDLastName(String firstName, String lastName);

}
