package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.controller.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.CommunityEmailAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireStationAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FloodAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonInfoAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PhoneAlertDTO;
import com.safetynet.safetynetalerts.service.AlertService;

@RestController
public class AlertController{

	@Autowired
	private AlertService alertService;
	
	@GetMapping("/firestation")
	public FireStationAlertDTO findPersonInfoByFireStationNumber(@RequestParam("stationNumber") int stationNumber) {
		//log info Firesta...
		
		//log info Dto
		return null;
	}
	
	@GetMapping("/childAlert")
	public ChildAlertDTO childAlert(@RequestParam("address") String address) {
		return null;
	}
	
	@GetMapping("/phoneAlert")
	public PhoneAlertDTO phoneAlert(@RequestParam("firestation") int firestation_number) {
		return null;
	}
	
	@GetMapping("/fire")
	public FireAlertDTO fireAlert(@RequestParam("address") String address) {
		return null;
	}
	
	@GetMapping("/flood/stations")
	public FloodAlertDTO floodAlert(@RequestParam("stations") List<Integer> aListOfStation_numbers) {
		return null;
	}
	
	@GetMapping("/personInfo")
	public PersonInfoAlertDTO findPersonInfoByFirstnameAndLastname(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		return null;
	}
	
	@GetMapping("/communityEmail")
	public CommunityEmailAlertDTO findCommunityEmailByCity(@RequestParam("city") String city) {
		return null;
	}
}
