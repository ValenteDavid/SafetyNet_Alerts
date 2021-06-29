package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	private static final Logger log = LoggerFactory.getLogger(MedicalRecordController.class);

	private static final String path = "/medicalRecord";

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping(path+"/{firstName}&{lastName}")
	public MedicalRecord get(@PathVariable String firstName, @PathVariable String lastName) {
		log.info("GET {}/{}&{} called", path, firstName, lastName);
		
		MedicalRecord medicalrecordFound = medicalRecordService.findByFirstNameANDLastName(firstName, lastName);
		log.debug("medicalrecordFound : {}", medicalrecordFound);
		
		if (medicalrecordFound == null) {
			throw new NotFoundException("No one found at this firstName : " + firstName + " lastName : " + lastName);
		}
		
		log.info("GET {}/{}&{} response body : {}", path, firstName, lastName,medicalrecordFound);
		return medicalrecordFound;
	}
	
	@PostMapping(path)
	public ResponseEntity<Void> save(@Valid @RequestBody MedicalRecord medicalRecord) {
		log.info("POST {} called body : {}", path, medicalRecord);
		
		MedicalRecord medicalrecordSave = medicalRecordService.save(medicalRecord);
		log.debug("medicalrecordSave : {}", medicalrecordSave);

		if (medicalrecordSave == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{firstName}&{lastName}")
				.buildAndExpand(medicalrecordSave.getFirstName(),medicalrecordSave.getLastName())
				.toUri();

		log.info("POST {} end", path);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path)
	public MedicalRecord update(@Valid @RequestBody MedicalRecord medicalRecord) {
		log.info("PUT {} called body : {}", path, medicalRecord);
		
		MedicalRecord medicalrecordUpdate = medicalRecordService.update(medicalRecord);
		log.debug("medicalrecordUpdate : {}", medicalrecordUpdate);
		
		if (medicalrecordUpdate == null) {
			throw new NotFoundException(
					"No found to update this medical record : " + medicalrecordUpdate);
		}
		
		log.info("PUT {} response body : {}", path, medicalrecordUpdate);
		return medicalrecordUpdate;
	}

	@DeleteMapping(path +"/{firstName}&{lastName}")
	public void delete(@PathVariable String firstName, @PathVariable String lastName) {
		log.info("DELETE {}/{}&{} called", path, firstName, lastName);
		
		if (!medicalRecordService.delete(firstName, lastName)) {
			throw new NotFoundException(
					"No one found at this first name : " + firstName + " last name : " + lastName);
		}
		
		log.info("DELETE {}/{}&{} end", path, firstName, lastName);
	}

}
