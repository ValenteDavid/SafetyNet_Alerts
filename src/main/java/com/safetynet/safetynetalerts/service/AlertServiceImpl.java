package com.safetynet.safetynetalerts.service;

import java.time.Instant;
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
		return null;
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
	public Iterable<String> listPersonPhoneByStationNumber(int stationNumber) {
		Set<String> setPhone = new HashSet<>();
		Stream<Person> stream = listPersonByStationNumber(stationNumber).stream();
		stream.forEach(x-> setPhone.add(x.getAddress()));
		return setPhone;
	}

}
