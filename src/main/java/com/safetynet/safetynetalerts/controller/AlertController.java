package com.safetynet.safetynetalerts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.controller.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.CommunityEmailAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireStationAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FloodAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonChildAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonFireStationAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonInfoAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonMedicalRecordDTO;
import com.safetynet.safetynetalerts.controller.dto.PhoneAlertDTO;
import com.safetynet.safetynetalerts.controller.exceptions.InvalidArgumentException;
import com.safetynet.safetynetalerts.controller.exceptions.NotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.AlertService;

@RestController
public class AlertController {

	@Autowired
	private AlertService alertService;

	@GetMapping("/firestation")
	public FireStationAlertDTO findPersonInfoByFireStationNumber(
			@RequestParam("stationNumber") int stationNumber) {
		FireStationAlertDTO fireStationAlertDTO = new FireStationAlertDTO();

		validStationNumber(stationNumber);

		List<Person> listPersons = alertService.listPersonByStationNumber(stationNumber);

		if (listPersons == null) {
			throw new NotFoundException("No one found at this station number : " + stationNumber);
		}

		List<PersonFireStationAlertDTO> personFireStationAlertDTOs = listPersons.stream()
				.map(person -> PersonFireStationAlertDTO.convertToDto(person))
				.collect(Collectors.toList());

		fireStationAlertDTO.setListPerson(personFireStationAlertDTOs);
		fireStationAlertDTO.setNumberOfAdults(alertService.numberOfAdult(listPersons));
		fireStationAlertDTO.setNumberOfChildren(alertService.numberOfChildren(listPersons));

		return fireStationAlertDTO;
	}

	@GetMapping("/childAlert")
	public ChildAlertDTO childAlert(@RequestParam("address") String address) {
		ChildAlertDTO childAlertDTO = new ChildAlertDTO();

		List<Person> listChidren = alertService.listChildren(
				alertService.listPersonByAddress(address));
		
		if (listChidren.isEmpty()) {
			return null;
		}

		childAlertDTO.setListChildren(listChidren.stream()
				.map(child -> PersonChildAlertDTO.convertToDto(child, alertService.ageOfPersonByPerson(child)))
				.collect(Collectors.toList()));

		return childAlertDTO;
	}

	@GetMapping("/phoneAlert")
	public PhoneAlertDTO phoneAlert(@RequestParam("firestation") int firestation_number) {
		PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();

		validStationNumber(firestation_number);

		List<String> listPhone = alertService.listPersonPhoneByStationNumber(firestation_number);

		if (listPhone == null) {
			throw new NotFoundException("No found person number at this station number : " + firestation_number);
		}

		phoneAlertDTO.setListNumbers(listPhone);

		return phoneAlertDTO;
	}

	@GetMapping("/fire")
	public FireAlertDTO fireAlert(@RequestParam("address") String address) {
		FireAlertDTO fireAlertDTO = new FireAlertDTO();

		List<Person> listPersons = alertService.listPersonByAddress(address);
		if (listPersons == null) {
			throw new NotFoundException("No found person number at this address : " + address);
		}

		List<PersonMedicalRecordDTO> personMedicalRecordDTOs = listPersons.stream()
				.map(person -> PersonMedicalRecordDTO.convertToDto(
						person,
						alertService.listMedicalRecordByFirstNameANDLastName(person.getFirstName(),
								person.getLastName()),
						alertService.ageOfPersonByPerson(person)))
				.distinct()
				.collect(Collectors.toList());

		fireAlertDTO.setListPersonsMedicalRecord(personMedicalRecordDTOs);

		Integer stationNumber = alertService.findStationNumberByAddress(address);
		if (stationNumber == null) {
			throw new NotFoundException("No found station number at this address : " + address);
		}

		fireAlertDTO.setStationNumber(stationNumber);

		return fireAlertDTO;
	}

	@GetMapping("/flood/stations")
	public FloodAlertDTO floodAlert(@RequestParam("stations") List<Integer> listOfStationNumbers) {
		FloodAlertDTO floodAlertDTO = new FloodAlertDTO();

		listOfStationNumbers.forEach(stationNumber -> validStationNumber(stationNumber));

		List<String> listAddress = listOfStationNumbers.stream()
				.map(stationNumber -> alertService.findAddressByStationNumber(stationNumber))
				.flatMap(allSationNumber -> allSationNumber.stream())
				.collect(Collectors.toList());

		if (listAddress.isEmpty()) {
			throw new NotFoundException("No found any address at this list station number : " + listOfStationNumbers);
		}

		Map<String, List<PersonMedicalRecordDTO>> mapAddressPerson = new HashMap<>();

		listAddress.forEach(addressFromStationNumber -> mapAddressPerson.put(
				addressFromStationNumber,
				listAddress.stream()
						.map(addressFromPerson -> alertService.listPersonByAddress(addressFromPerson))
						.flatMap(addressFromPerson -> addressFromPerson.stream())
						.map(person -> PersonMedicalRecordDTO.convertToDto(
								person,
								alertService.listMedicalRecordByFirstNameANDLastName(
										person.getFirstName(), person.getLastName()),
								alertService.ageOfPersonByPerson(person)))
						.collect(Collectors.toList())));
		floodAlertDTO.setMapAddressPerson(mapAddressPerson);

		return floodAlertDTO;
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

	private void validStationNumber(int stationNumber) {
		if (stationNumber <= 0) {
			throw new InvalidArgumentException(
					"The station number cannot be less than or equal to 0 : " + stationNumber);
		}
	}
}
