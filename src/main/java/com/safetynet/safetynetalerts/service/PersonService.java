package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonService {

	public Person findByFirstNameANDLastName(String firstName,String lastName);
	public Iterable<Person> findAllByAddress(String address);
	public Iterable<Person> findAll();
	public Person save(Person person);
	public Person update (Person person);
	public void delete(Person person);
	
}
