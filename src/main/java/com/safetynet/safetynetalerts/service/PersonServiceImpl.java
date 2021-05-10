package com.safetynet.safetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonDao;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public Person findByFirstNameANDLastName(String firstName, String lastName) {
		return null;
	}

	@Override
	public Iterable<Person> findAllByAddress(String address) {
		return null;
	}

	@Override
	public Iterable<Person> findAll() {
		return null;
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


}
