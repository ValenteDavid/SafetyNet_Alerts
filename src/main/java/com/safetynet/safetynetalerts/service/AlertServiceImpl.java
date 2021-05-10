package com.safetynet.safetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.Person;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private PersonService personService;
	@Autowired
	private FireStationService fireStationService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Override
	public Iterable<Person> listPersonByAddress(String address) {
		return null;
	}
	@Override
	public Iterable<Person> listChildren(Person... persons) {
		return null;
	}
	@Override
	public Iterable<Person> listAdult(Person... persons) {
		return null;
	}
	@Override
	public int numberOfChildren(Person... persons) {
		return 0;
	}
	@Override
	public int numberOfAdult(Person... persons) {
		return 0;
	}
	@Override
	public Iterable<String> listEmail(Person... persons) {
		return null;
	}

	

}
