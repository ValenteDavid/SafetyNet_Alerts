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
				.findFirst()
				.get();

		return personFound;
	}

	@Override
	public List<Person> findAll() {
		return persons;
	}

	@Override
	public Person save(Person person) {
		return persons.add(person)?person:null;
	}

	@Override
	public Person update(Person person) {
		persons.remove(findByFirstNameANDLastName(person.getFirstName(),person.getLastName()));
		persons.add(person);
		return person;
	}

	@Override
	public void delete(Person person) {

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
