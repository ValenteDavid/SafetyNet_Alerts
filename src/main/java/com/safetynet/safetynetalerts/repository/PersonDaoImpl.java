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
		return null;
	}

	@Override
	public List<Person> findAll() {
		return persons;
	}

	@Override
	public Person save(Person person) {
		return null;
	}

	@Override
	public Person update(Person person) {
		return null;
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
}
