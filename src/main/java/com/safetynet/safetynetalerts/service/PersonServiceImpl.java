package com.safetynet.safetynetalerts.service;

import java.util.List;

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
	public List<Person> findAllByAddress(String address) {
		return personDao.findAllByAddress(address);
	}

	@Override
	public List<Person> findAll() {
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
