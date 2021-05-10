package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

@RestController
public class PersonController{
	
	private static final String path = "/person";

	@Autowired
	private PersonService personService;

	@PostMapping(path)
	public Person add(@RequestBody Person person) {
		return null;
	}

	@PatchMapping(path)
	public Person update(@RequestBody Person person) {
		return null;
	}
	
	@DeleteMapping(path+"/{firstName}&{lastName}")
	public void delete(String firstName, String lastName) {
	}
	
}
