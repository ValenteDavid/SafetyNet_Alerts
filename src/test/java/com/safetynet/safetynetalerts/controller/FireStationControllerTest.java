package com.safetynet.safetynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FireStationService fireStationService;
	
	@Test
	void testGet() throws Exception {
		String address = "address";
		String stationNumber = "1";
		
		when(fireStationService.findByAddressANDStationNumber(address, Integer.parseInt(stationNumber))).thenReturn(new FireStation(address,  Integer.parseInt(stationNumber)));
		
		mockMvc.perform(get("/firestation/{0}&{1}",address,stationNumber))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGet_thenReturnNull() throws Exception {
		String address = "address";
		String stationNumber = "1";
		
		when(fireStationService.findByAddressANDStationNumber(address, Integer.parseInt(stationNumber))).thenReturn(null);
		
		mockMvc.perform(get("/firestation/{0}&{1}",address,stationNumber))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void testAdd() throws Exception {
		String address = "address";
		int stationNumber = 1;
		
		FireStation fireStation = new FireStation(address, stationNumber);
		String json = new ObjectMapper().writeValueAsString(fireStation);
		
		when(fireStationService.save(any())).thenReturn(fireStation);
		
		MvcResult mvcResult = mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated())
				.andReturn();
		
		Pattern uriExpect = Pattern.compile(".*/firestation/"+address+"&"+stationNumber);
		assertTrue(uriExpect.matcher(mvcResult.getResponse().getRedirectedUrl()).matches());
	}

	@Test
	void testUpdate() throws Exception {
		FireStation fireStation = new FireStation("Address", 777);
		String json = new ObjectMapper().writeValueAsString(fireStation);
		
		when(fireStationService.update(any())).thenReturn(fireStation);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void testUpdate_thenReturnNull() throws Exception {
		FireStation fireStation = new FireStation("Address", 777);
		String json = new ObjectMapper().writeValueAsString(fireStation);
		
		when(fireStationService.update(any())).thenReturn(null);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDelete() throws Exception {
		String address = "address";
		String stationNumber = "1";
		
		when(fireStationService.delete(address, Integer.parseInt(stationNumber))).thenReturn(true);
		
		mockMvc.perform(delete("/firestation/{0}&{1}",address,stationNumber))
				.andExpect(status().isOk());
	}
	
	@Test
	void testDelete_thenReturnFalse() throws Exception {
		String address = "address";
		String stationNumber = "1";
		
		when(fireStationService.delete(address, Integer.parseInt(stationNumber))).thenReturn(false);
		
		mockMvc.perform(delete("/firestation/{0}&{1}",address,stationNumber))
				.andExpect(status().isNotFound());
	}

}
