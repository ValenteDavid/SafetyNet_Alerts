package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonService {

	public Person findByFirstNameANDLastName(String firstName, String lastName);

	public List<Person> findAllByAddress(String address);

	public List<Person> findAll();

	public Person save(Person person);

	public Person update(Person person);

	public boolean delete(String firstName, String lastName);

	public List<Person> findByCity(String city);
}
