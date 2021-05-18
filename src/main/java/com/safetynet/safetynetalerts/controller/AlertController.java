package com.safetynet.safetynetalerts.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.controller.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.CommunityEmailAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FireStationAlertDTO;
import com.safetynet.safetynetalerts.controller.dto.FloodAlertDTO;
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
		return null;
	}

	@GetMapping("/phoneAlert")
	public PhoneAlertDTO phoneAlert(@RequestParam("firestation") int firestation_number) {

		PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();

		validStationNumber(firestation_number);
		
		Collection<String> iterablePhone = alertService.listPersonPhoneByStationNumber(firestation_number);

		if (iterablePhone == null) {
			throw new NotFoundException("No found person number at this station number : " + firestation_number);
		}

		phoneAlertDTO.setListNumbers(new HashSet<>(iterablePhone));

		return phoneAlertDTO;
	}

	@GetMapping("/fire")
	public FireAlertDTO fireAlert(@RequestParam("address") String address) {

		FireAlertDTO fireAlertDTO = new FireAlertDTO();

		Iterable<Person> iterablePerson = alertService.listPersonByAddress(address);
		if (iterablePerson == null) {
			throw new NotFoundException("No found person number at this address : " + address);
		}

		Set<PersonMedicalRecordDTO> personMedicalRecordDTOs = new HashSet<>();

		Stream<Person> stream = StreamSupport.stream(iterablePerson.spliterator(), false);
		stream.forEach(x -> personMedicalRecordDTOs.add(
				PersonMedicalRecordDTO.convertToDto(
						x,
						alertService.listMedicalRecordByFirstNameANDLastName(x.getFirstName(), x.getLastName()),
						alertService.ageOfPersonByPerson(x))));

		fireAlertDTO.setListPersonMedicalRecord(personMedicalRecordDTOs);

		Integer stationNumber = alertService.findStationNumberByAddress(address);
		if (stationNumber == null) {
			throw new NotFoundException("No found station number at this address : " + address);
		}

		fireAlertDTO.setStationNumber(stationNumber);

		return fireAlertDTO;
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
	
	private void validStationNumber(int stationNumber) {
		if (stationNumber <= 0) {
			throw new InvalidArgumentException("The station number cannot be less than or equal to 0 : " + stationNumber);
		}
	}
}
