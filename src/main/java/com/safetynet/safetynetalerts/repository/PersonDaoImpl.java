package com.safetynet.safetynetalerts.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	public static List<Person> persons;

	@Override
	public Person findByFirstNameANDLastName(String firstName, String lastName) {
		Person personFound = persons.stream()
				.filter(person -> (person.getFirstName().contentEquals(firstName)))
				.filter(person -> (person.getLastName().contentEquals(lastName)))
				.findFirst().orElse(null);
		return personFound;
	}

	@Override
	public List<Person> findAll() {
		return persons;
	}

	@Override
	public Person save(Person person) {
		Person personAdd = persons.add(person) ? person : null;
		DataFile.saveFile();
		return personAdd;
	}

	@Override
	public Person update(Person person) {
		Person personUpdate = persons.stream()
				.filter(x -> person.getFirstName().equals(x.getFirstName()))
				.filter(x -> person.getLastName().equals(x.getLastName()))
				.findFirst().orElse(null);

		if (personUpdate == null) {
			return null;
		}
		persons.remove(personUpdate);
		persons.add(person);
		DataFile.saveFile();
		return person;
	}

	@Override
	public boolean delete(Person person) {
		boolean response = person == null ? false : persons.remove(person);
		DataFile.saveFile();
		return response;
	}

	@Override
	public List<Person> findAllByAddress(String address) {
		List<Person> listPersons = persons.stream()
				.filter(person -> person.getAddress().contentEquals(address))
				.distinct()
				.collect(Collectors.toList());

		return listPersons;
	}

	@Override
	public List<Person> findByCity(String city) {
		List<Person> listPersons = persons.stream()
				.filter(person -> person.getCity().contentEquals(city))
				.distinct()
				.collect(Collectors.toList());
		return listPersons;
	}
}
