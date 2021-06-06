package com.safetynet.safetynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MedicalRecordService medicalRecordService;

	@Test
	void testGet() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		when(medicalRecordService.findByFirstNameANDLastName(firstName, lastName)).thenReturn(new MedicalRecord(firstName, lastName, new Date(), null, null));
		
		mockMvc.perform(get("/medicalRecord/{0}&{1}",firstName,lastName))
		.andExpect(status().isOk());
		
	}
	@Test
	void testGet_thenReturnNull() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		when(medicalRecordService.findByFirstNameANDLastName(firstName, lastName)).thenReturn(null);
		
		mockMvc.perform(get("/medicalRecord/{0}&{1}",firstName,lastName))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	void testAdd() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, null,
				new String[] { "aznol:350mg", "hydrapermazol:100mg" }, new String[] { "nillacilan" });
		medicalRecord.setBirthdate("6/6/2012");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);
		
		when(medicalRecordService.save(any())).thenReturn(medicalRecord);
		
		MvcResult mvcResult = mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated())
				.andReturn();
		
		Pattern uriExpect = Pattern.compile(".*/medicalRecord/"+firstName+"&"+lastName);
		assertTrue(uriExpect.matcher(mvcResult.getResponse().getRedirectedUrl()).matches());
	}

	@Test
	void testUpdate() throws Exception{
		MedicalRecord medicalRecord = new MedicalRecord("Firstname", "Lastname", null,
				new String[] { "aznol:350mg", "hydrapermazol:100mg" }, new String[] { "nillacilan" });
		medicalRecord.setBirthdate("6/6/2012");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);
		
		when(medicalRecordService.update(any())).thenReturn(medicalRecord);

		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void testUpdate_thenReturnNull() throws Exception{
		MedicalRecord medicalRecord = new MedicalRecord("Firstname", "Lastname", null,
				new String[] { "aznol:350mg", "hydrapermazol:100mg" }, new String[] { "nillacilan" });
		medicalRecord.setBirthdate("6/6/2012");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);
		
		when(medicalRecordService.update(any())).thenReturn(null);

		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDelete() throws Exception{
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		when(medicalRecordService.delete(firstName, lastName)).thenReturn(true);
		
		mockMvc.perform(delete("/medicalRecord/{0}&{1}",firstName,lastName))
		.andExpect(status().isOk());
	}
	
	@Test
	void testDelete_thenReturnFalse() throws Exception{
		String firstName = "Firstname";
		String lastName = "Lastname";
		
		when(medicalRecordService.delete(firstName, lastName)).thenReturn(false);
		
		mockMvc.perform(delete("/medicalRecord/{0}&{1}",firstName,lastName))
		.andExpect(status().isNotFound());
	}

}
