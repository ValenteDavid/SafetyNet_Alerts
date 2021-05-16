package com.safetynet.safetynetalerts.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private PersonService personService;
	@Autowired
	private FireStationService fireStationService;
	@Autowired
	private MedicalRecordService medicalRecordService;

	@Override
	public Collection<Person> listPersonByStationNumber(int stationNumber) {
		Set<Person> iterablePerson = new HashSet<Person>();

		Iterable<String> iterableAddress = fireStationService.findAddressByStationNumber(stationNumber);

		Stream<String> stream = StreamSupport.stream(iterableAddress.spliterator(), false);

		stream.forEach(x -> iterablePerson.addAll(personService.findAllByAddress(x)));

		return iterablePerson;
	}

	@Override
	public Iterable<Person> listPersonByAddress(String address) {
		return personService.findAllByAddress(address);
	}

	@Override
	public Collection<Person> listChildren(Person... persons) {
		List<Person> listPerson = Arrays.asList(persons);
		List<Person> listChildren = new ArrayList<>();

		Stream<Person> stream = listPerson.stream();
		stream.filter(x -> calculDateMaxChildren(x).compareTo(Calendar.getInstance().toInstant()) > 0)
				.forEach(x -> listChildren.add(x));

		return listChildren;
	}

	@Override
	public Collection<Person> listAdult(Person... persons) {
		List<Person> listPerson = Arrays.asList(persons);
		List<Person> listAdult = new ArrayList<>();

		Stream<Person> stream = listPerson.stream();
		stream.filter(x -> calculDateMaxChildren(x).compareTo(Calendar.getInstance().toInstant()) <= 0)
				.forEach(x -> listAdult.add(x));

		return listAdult;
	}

	@Override
	public int numberOfChildren(Person... persons) {
		return (listChildren(persons)).size();
	}

	@Override
	public int numberOfAdult(Person... persons) {
		return (listAdult(persons)).size();
	}

	@Override
	public Iterable<String> listEmail(Person... persons) {
		return null;
	}

	private Instant calculDateMaxChildren(Person person) {
		Date birthdate = medicalRecordService.findByFirstNameANDLastName(person.getFirstName(), person.getLastName())
				.getBirthdate();

		Calendar birthdateCalendar = GregorianCalendar.getInstance();
		birthdateCalendar.setTime(birthdate);
		birthdateCalendar.add(Calendar.YEAR, 19);
		Instant birthdateInstant = birthdateCalendar.toInstant();

		return birthdateInstant;
	}

	@Override
	public Collection<String> listPersonPhoneByStationNumber(int stationNumber) {
		Set<String> setPhone = new HashSet<>();
		Stream<Person> stream = listPersonByStationNumber(stationNumber).stream();
		stream.forEach(x -> setPhone.add(x.getPhone()));
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
	public int ageOfPersonByBirthdate(Date birthdate) {
		Calendar birthdateCalendar = Calendar.getInstance();
		birthdateCalendar.setTime(birthdate);
		int year = birthdateCalendar.get(Calendar.YEAR);
		int month = birthdateCalendar.get(Calendar.MONTH) + 1;
		int date = birthdateCalendar.get(Calendar.DATE);
		
		LocalDate l1 = LocalDate.of(year, month, date);
		LocalDate now1 = LocalDate.now();
		Period diff1 = Period.between(l1, now1);
		
		int age = diff1.getYears();

		return age;
	}

	@Override
	public int ageOfPersonByPerson(Person person) {
		Date birthdate = medicalRecordService.findByFirstNameANDLastName(person.getFirstName(), person.getLastName())
				.getBirthdate();
		int age = ageOfPersonByBirthdate(birthdate);
		return age;
	}

}
