package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;

public interface AlertService {
	
	public Iterable<Person> listPersonByAddress(String address);
	public Iterable<Person> listChildren(Person... persons);
	public Iterable<Person> listAdult(Person... persons);
	
	public int numberOfChildren(Person... persons);
	public int numberOfAdult(Person... persons);
	
	public Iterable<String> listEmail(Person... persons);
	
}
