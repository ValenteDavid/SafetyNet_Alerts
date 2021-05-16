package com.safetynet.safetynetalerts.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	public Iterable<Person> findAll() {
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
	public Collection<Person> findAllByAddress(String address) {
		Set<Person> setPerson = new HashSet<>();

		Stream<Person> stream = StreamSupport.stream(persons.spliterator(), false);
		stream.filter(x -> x.getAddress().contentEquals(address))
				.forEach(x -> setPerson.add(x));
		return setPerson;
	}
}
