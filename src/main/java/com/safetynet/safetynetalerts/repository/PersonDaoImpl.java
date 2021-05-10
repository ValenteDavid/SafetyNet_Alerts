package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao{
	
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
	public Iterable<Person> findAllByAddress(String address) {
		return null;
	}
}
	