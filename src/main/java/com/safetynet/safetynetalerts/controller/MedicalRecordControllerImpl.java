package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordControllerImpl{

	private static final String path = "/medicalRecord";
	
	@Autowired
	private MedicalRecordService medicalRecordService;

	@PostMapping(path)
	public MedicalRecord add(@RequestBody MedicalRecord medicalRecord) {
		return null;
	}

	@PatchMapping(path)
	public MedicalRecord update(@RequestBody MedicalRecord medicalRecord) {
		return null;
	}

	@DeleteMapping(path +"/{address}&{stationNumber}")
	public void delete(@PathVariable String firstName, @PathVariable String lastName) {
		
	}
	
}
