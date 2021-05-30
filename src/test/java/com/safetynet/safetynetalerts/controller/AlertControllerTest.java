package com.safetynet.safetynetalerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.AlertService;

@WebMvcTest(controllers = AlertController.class)
public class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlertService alertService;

	@Test
	public void testfindPersonInfoByFireStationNumber_Status_ValidArgument() throws Exception {
		String stationNumber = "1";
		when(alertService.listPersonByStationNumber(1)).thenReturn(Mockito.<Person>anyList());

		mockMvc.perform(get("/firestation")
				.param("stationNumber", stationNumber))
				.andExpect(status().isOk());
	}

	@ParameterizedTest(name = "stationNumber : {0}")
	@ValueSource(strings = { "aaa", "-1" })
	public void testfindPersonInfoByFireStationNumber_Status_InvalidFormatArgument(String stationNumber)
			throws Exception {
		mockMvc.perform(get("/firestation")
				.param("stationNumber", stationNumber))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testfindPersonInfoByFireStationNumber_Status_NotFound() throws Exception {
		String stationNumber = "1";
		when(alertService.listPersonByStationNumber(1)).thenReturn(null);

		mockMvc.perform(get("/firestation")
				.param("stationNumber", stationNumber))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testphoneAlert_Status_ValidArgument() throws Exception {
		String firestation = "1";
		when(alertService.listPersonPhoneByStationNumber(1)).thenReturn(Mockito.<String>anyList());

		mockMvc.perform(get("/phoneAlert")
				.param("firestation", firestation))
				.andExpect(status().isOk());
	}

	@ParameterizedTest(name = "firestation : {0}")
	@ValueSource(strings = { "aaa", "-1" })
	public void testphoneAlert_Status_InvalidFormatArgument(String firestation) throws Exception {
		mockMvc.perform(get("/phoneAlert")
				.param("firestation", firestation))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testphoneAlert_Status_NotFound() throws Exception {
		String firestation = "1";
		when(alertService.listPersonPhoneByStationNumber(1)).thenReturn(null);

		mockMvc.perform(get("/phoneAlert")
				.param("firestation", firestation))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testfireAlert_Status_ValidArgument() throws Exception {
		String address = "anyAddress";
		when(alertService.listPersonByAddress(address)).thenReturn(Mockito.<Person>anyList());

		mockMvc.perform(get("/fire")
				.param("address", address))
				.andExpect(status().isOk());
	}

	@Test
	public void testfireAlert_Status_NotFound() throws Exception {
		String address = "anyAddress";
		when(alertService.listPersonByAddress(address)).thenReturn(null);

		mockMvc.perform(get("/fire")
				.param("address", address))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testfloodAlert_Status_ValidArgument() throws Exception {
		String listOfStationNumbers = "1,2";
		List<Person> listPersons = new ArrayList<>();
		listPersons.add(new Person("firstName", "lastName"));
		
		List<String> listAddresses = new ArrayList<>();
		listAddresses.add("address");
		
		when(alertService.findAddressByStationNumber(1)).thenReturn(listAddresses);
		when(alertService.findAddressByStationNumber(2)).thenReturn(listAddresses);
		when(alertService.listPersonByAddress("address")).thenReturn(listPersons);

		when(alertService.listMedicalRecordByFirstNameANDLastName(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new MedicalRecord());
		when(alertService.ageOfPersonByPerson(Mockito.any(Person.class))).thenReturn(1);

		mockMvc.perform(get("/flood/stations")
				.param("stations", listOfStationNumbers))
				.andExpect(status().isOk());
	}

	@ParameterizedTest(name = "listOfStationNumbers : {0}")
	@ValueSource(strings = { "aaa", "-1","1,aaa","-1,1" })
	public void testfloodAlert_Status_InvalidFormatArgument(String listOfStationNumbers) throws Exception {

		when(alertService.findAddressByStationNumber(Mockito.anyInt())).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/flood/stations")
				.param("stations", listOfStationNumbers))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testfloodAlert_Status_NotFound() throws Exception {
		String listOfStationNumbers = "1,2";

		when(alertService.findAddressByStationNumber(Mockito.anyInt())).thenReturn(new ArrayList<>());
		when(alertService.listPersonByAddress(Mockito.anyString())).thenReturn(new ArrayList<>());

		when(alertService.listMedicalRecordByFirstNameANDLastName(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new MedicalRecord());
		when(alertService.ageOfPersonByPerson(Mockito.any(Person.class))).thenReturn(1);

		mockMvc.perform(get("/flood/stations")
				.param("stations", listOfStationNumbers))
				.andExpect(status().isNotFound());
	}

	@ParameterizedTest(name = "firstName : {0} ,lastName : {1}")
	@CsvSource({"Firstname,Lastname","First-Name,Last-Name","First Name,Last Name" })
	public void testfindPersonInfoByFirstnameAndLastname_Status_ValidArgument(String firstName,String lastName) throws Exception {
		
		List<Person> listPerson = new ArrayList<Person>();
		Person person = new Person(firstName, lastName);
		listPerson.add(person);
		MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, null, null, null);
		when(alertService.listPersonByFirstNameANDLastName(Mockito.anyString(),Mockito.anyString())).thenReturn(listPerson);
		
		when(alertService.listMedicalRecordByFirstNameANDLastName(firstName, lastName)).thenReturn(medicalRecord);
		when(alertService.ageOfPersonByPerson(person)).thenReturn(1);
		
		mockMvc.perform(get("/personInfo")
				.param("firstName", firstName)
				.param("lastName", lastName))
				.andExpect(status().isOk());
	}

	@Test
	public void testfindPersonInfoByFirstnameAndLastname_Status_NotFound() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		when(alertService.listPersonByFirstNameANDLastName(firstName, lastName)).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/personInfo")
				.param("firstName", firstName)
				.param("lastName", lastName))
				.andExpect(status().isNotFound());
	}
}
