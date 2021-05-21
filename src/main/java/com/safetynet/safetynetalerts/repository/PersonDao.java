package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonDao {

	public List<Person> findByFirstNameANDLastName(String firstName, String lastName);

	public List<Person> findAllByAddress(String address);

	public List<Person> findAll();

	public Person save(Person person);

	public Person update(Person person);

	public void delete(Person person);
}
