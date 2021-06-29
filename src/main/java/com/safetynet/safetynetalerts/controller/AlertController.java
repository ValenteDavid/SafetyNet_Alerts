package com.safetynet.safetynetalerts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.safetynet.safetynetalerts.controller.dto.PersonInfoDTO;
import com.safetynet.safetynetalerts.controller.dto.PersonMedicalRecordDTO;
import com.safetynet.safetynetalerts.controller.dto.PhoneAlertDTO;
import com.safetynet.safetynetalerts.controller.exceptions.NotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.AlertService;

@RestController
public class AlertController {

	private static final Logger log = LoggerFactory.getLogger(AlertController.class);

	@Autowired
	private AlertService alertService;

	@GetMapping("/firestation")
	public FireStationAlertDTO findPersonInfoByFireStationNumber(
			@RequestParam("stationNumber") int stationNumber) {
		log.info("GET /firestation called params : stationNumber = {}", stationNumber);

		FireStationAlertDTO fireStationAlertDTO = new FireStationAlertDTO();

		List<Person> listPersons = alertService.listPersonByStationNumber(stationNumber);
		log.debug("listPersons : {}", listPersons);

		if (listPersons == null) {
			throw new NotFoundException("No one found at this station number : " + stationNumber);
		}

		List<PersonFireStationAlertDTO> personFireStationAlertDTOs = listPersons.stream()
				.map(person -> PersonFireStationAlertDTO.convertToDto(person))
				.collect(Collectors.toList());
		log.debug("personFireStationAlertDTOs : {}", personFireStationAlertDTOs);
		fireStationAlertDTO.setListPerson(personFireStationAlertDTOs);

		int numberOfAdult = alertService.numberOfAdult(listPersons);
		log.debug("numberOfAdult : {}", listPersons);
		fireStationAlertDTO.setNumberOfAdults(numberOfAdult);

		int numberOfChildren = alertService.numberOfChildren(listPersons);
		fireStationAlertDTO.setNumberOfChildren(numberOfChildren);
		log.debug("numberOfChildren : {}", numberOfChildren);

		log.info("GET /firestation response body {}",fireStationAlertDTO);
		return fireStationAlertDTO;		
	}

	@GetMapping("/childAlert")
	public ChildAlertDTO childAlert(@RequestParam("address") String address) {
		log.info("GET /childAlert called params : address = {}",address);
		
		ChildAlertDTO childAlertDTO = new ChildAlertDTO();
		
		List<Person> listChidren = alertService.listChildren(
				alertService.listPersonByAddress(address));
		log.debug("listChidren : {}", listChidren);
		
		childAlertDTO.setListChildren(listChidren.stream()
				.map(child -> PersonChildAlertDTO.convertToDto(child, alertService.ageOfPersonByPerson(child)))
				.collect(Collectors.toList()));

		log.info("GET /childAlert response body {}",childAlertDTO);
		return childAlertDTO;
	}

	@GetMapping("/phoneAlert")
	public PhoneAlertDTO phoneAlert(@RequestParam("firestation") int firestation_number) {
		PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
		log.info("GET /phoneAlert called params : firestation = {}",firestation_number);

		List<String> listPhone = alertService.listPersonPhoneByStationNumber(firestation_number);
		log.debug("listPhone : {}", listPhone);
		
		if (listPhone == null) {
			throw new NotFoundException("No found person number at this station number : " + firestation_number);
		}

		phoneAlertDTO.setListNumbers(listPhone);

		log.info("GET /phoneAlert response body {}",phoneAlertDTO);
		return phoneAlertDTO;
	}

	@GetMapping("/fire")
	public FireAlertDTO fireAlert(@RequestParam("address") String address) {
		FireAlertDTO fireAlertDTO = new FireAlertDTO();
		log.info("GET /fire called params : address = {}",address);

		List<Person> listPersons = alertService.listPersonByAddress(address);
		log.debug("listPersons : {}", listPersons);
		
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
		log.debug("personMedicalRecordDTOs : {}", personMedicalRecordDTOs);
		fireAlertDTO.setListPersonsMedicalRecord(personMedicalRecordDTOs);

		Integer stationNumber = alertService.findStationNumberByAddress(address);
		log.debug("stationNumber : {}", stationNumber);
		if (stationNumber == null) {
			throw new NotFoundException("No found station number at this address : " + address);
		}
		fireAlertDTO.setStationNumber(stationNumber);

		log.info("GET /fire response body {}",fireAlertDTO);
		return fireAlertDTO;
	}

	@GetMapping("/flood/stations")
	public FloodAlertDTO floodAlert(@RequestParam("stations") List<Integer> listOfStationNumbers) {
		FloodAlertDTO floodAlertDTO = new FloodAlertDTO();
		log.info("GET /flood called params : stations = {}",listOfStationNumbers);

		List<String> listAddress = listOfStationNumbers.stream()
				.map(stationNumber -> alertService.findAddressByStationNumber(stationNumber))
				.flatMap(allSationNumber -> allSationNumber.stream())
				.collect(Collectors.toList());
		log.debug("listAddress : {}", listAddress);

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
		log.debug("mapAddressPerson : {}", mapAddressPerson);
		floodAlertDTO.setMapAddressPerson(mapAddressPerson);

		log.info("GET /flood response body {}",floodAlertDTO);
		return floodAlertDTO;
	}

	@GetMapping("/personInfo")
	public PersonInfoAlertDTO findPersonInfoByFirstnameAndLastname(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		log.info("GET /personInfo called params : firstName = {}, lastName = {}",firstName,lastName);
		PersonInfoAlertDTO personInfoAlertDTO = new PersonInfoAlertDTO();

		List<Person> listPersons = alertService.listPersonByFirstNameANDLastName(firstName, lastName);
		log.debug("listPersons : {}", listPersons);
		
		if (listPersons.isEmpty()) {
			throw new NotFoundException(
					"No person has been found with this first name : " + firstName + " and last name : " + lastName);
		}

		personInfoAlertDTO.setListPersonInfo(
				listPersons.stream()
						.map(person -> PersonInfoDTO.convertToDto(
								person,
								alertService.listMedicalRecordByFirstNameANDLastName(firstName, lastName),
								alertService.ageOfPersonByPerson(person)))
						.collect(Collectors.toList()));
		
		log.info("GET /personInfo response body {}",personInfoAlertDTO);
		return personInfoAlertDTO;
	}

	@GetMapping("/communityEmail")
	public CommunityEmailAlertDTO findCommunityEmailByCity(@RequestParam("city") String city) {
		CommunityEmailAlertDTO communityEmailAlertDTO = new CommunityEmailAlertDTO();
		log.info("GET /communityEmail called params : city = {}",city);

		List<String> listEmail = alertService.listEmail(alertService.listPersonByCity(city));
		log.debug("listEmail : {}", listEmail);
		
		communityEmailAlertDTO.setListEmail(listEmail);

		log.info("GET /communityEmail response {}",communityEmailAlertDTO);
		return communityEmailAlertDTO;
	}

}
