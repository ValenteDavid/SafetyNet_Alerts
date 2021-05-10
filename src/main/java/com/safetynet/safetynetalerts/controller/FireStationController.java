package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationServiceImpl;

@RestController
public class FireStationController{

	private static final String path = "/firestation";
	
	@Autowired
	private FireStationServiceImpl fireStationService;

	@PostMapping(path)
	public FireStation add(@RequestBody FireStation fireStation) {
		return null;
	}

	@PatchMapping(path)
	public FireStation update(@RequestBody FireStation fireStation) {
		return null;
	}

	@DeleteMapping(path +"/{address}&{stationNumber}")
	public void delete(@PathVariable("address") String adresse, @PathVariable("stationNumber") int stationNumber) {
	}
	
}
