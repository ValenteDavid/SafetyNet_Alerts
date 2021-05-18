package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonType;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private PersonService personService;
	@Autowired
	private FireStationService fireStationService;
	@Autowired
	private MedicalRecordService medicalRecordService;

	@Override
	public List<Person> listPersonByStationNumber(int stationNumber) {

		List<Person> listPersons = fireStationService.findAddressByStationNumber(stationNumber).stream()
				.map(address -> personService.findAllByAddress(address))
				.flatMap(listAll -> listAll.stream())
				.collect(Collectors.toList());

		return listPersons;
	}

	@Override
	public List<Person> listPersonByAddress(String address) {
		return personService.findAllByAddress(address);
	}

	@Override
	public int ageOfPersonByBirthdate(Date birthdate) {
		Calendar birthdateCalendar = Calendar.getInstance();
		birthdateCalendar.setTime(birthdate);

		LocalDate birthdateLocale = LocalDate.of(
				birthdateCalendar.get(Calendar.YEAR),
				birthdateCalendar.get(Calendar.MONTH) + 1,
				birthdateCalendar.get(Calendar.DATE));
		LocalDate nowLocale = LocalDate.now();

		int age = Period.between(birthdateLocale, nowLocale).getYears();

		return age;
	}

	@Override
	public int ageOfPersonByPerson(Person person) {
		Date birthdate = medicalRecordService.findByFirstNameANDLastName(
				person.getFirstName(),
				person.getLastName()).getBirthdate();
		int age = ageOfPersonByBirthdate(birthdate);
		return age;
	}

	private PersonType getPersonType(Person person) {

		int age = ageOfPersonByPerson(person);

		if (age <= PersonType.CHILD.getMaxAge()) {
			return PersonType.CHILD;
		} else if (age <= PersonType.ADULT.getMaxAge()) {
			return PersonType.ADULT;
		}

		return null;
	}

	private boolean isChildren(Person person) {
		return getPersonType(person) == PersonType.CHILD;
	}

	private boolean isAdult(Person person) {
		return getPersonType(person) == PersonType.ADULT;
	}

	@Override
	public List<Person> listChildren(List<Person> listPersons) {

		List<Person> listChildren = listPersons.stream()
				.filter(person -> isChildren(person))
				.collect(Collectors.toList());

		return listChildren;
	}

	@Override
	public List<Person> listAdult(List<Person> listPersons) {

		List<Person> listAdult = listPersons.stream()
				.filter(person -> isAdult(person))
				.collect(Collectors.toList());

		return listAdult;
	}

	@Override
	public int numberOfChildren(List<Person> listPersons) {
		return (listChildren(listPersons)).size();
	}

	@Override
	public int numberOfAdult(List<Person> listPersons) {
		return (listAdult(listPersons)).size();
	}

	@Override
	public List<String> listEmail(List<Person> listPersons) {
		return null;
	}

	@Override
	public List<String> listPersonPhoneByStationNumber(int stationNumber) {

		List<String> setPhone = listPersonByStationNumber(stationNumber).stream()
				.map(person -> person.getPhone())
				.distinct()
				.collect(Collectors.toList());

		return setPhone;
	}

	@Override
	public Integer findStationNumberByAddress(String Address) {
		return fireStationService.findStationNumberByAddress(Address);
	}

	@Override
	public MedicalRecord listMedicalRecordByFirstNameANDLastName(String firstName, String lastName) {
		return medicalRecordService.findByFirstNameANDLastName(firstName, lastName);
	}

	@Override
	public List<String> findAddressByStationNumber(int stationNumber) {
		return fireStationService.findAddressByStationNumber(stationNumber);
	}

}
