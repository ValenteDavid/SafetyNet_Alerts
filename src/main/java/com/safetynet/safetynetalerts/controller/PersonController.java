package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.controller.exceptions.NotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	private static final String path = "/person";

	@Autowired
	private PersonService personService;

	@GetMapping(path+"/{firstName}&{lastName}")
	public Person get(@PathVariable String firstName, @PathVariable String lastName) {
		Person personFound = personService.findByFirstNameANDLastName(firstName, lastName);
		
		if (personFound == null) {
			throw new NotFoundException("No one found at this firstName : " + firstName + " lastName : " + lastName);
		}

		return personFound;
	}
	
	@PostMapping(path)
	public ResponseEntity<Void> save(@Valid @RequestBody Person person) {
		Person personSave = personService.save(person);

		if (personSave == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{firstName}&{lastName}")
				.buildAndExpand(personSave.getFirstName(),personSave.getLastName())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path)
	public Person update(@Valid @RequestBody Person person) {
		return personService.update(person);
	}

	@DeleteMapping(path+"/{firstName}&{lastName}")
	public void delete(@PathVariable String firstName, @PathVariable String lastName) {
		personService.delete(personService.findByFirstNameANDLastName(firstName, lastName));
	}
	
}
