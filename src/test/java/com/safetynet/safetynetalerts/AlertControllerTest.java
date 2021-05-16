package com.safetynet.safetynetalerts;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.controller.AlertController;
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
		when(alertService.listPersonByStationNumber(1)).thenReturn(Mockito.<Person>anyCollection());
		
		mockMvc.perform(get("/firestation")
				.param("stationNumber", stationNumber))
				.andExpect(status().isOk());
	}

	@ParameterizedTest(name = "stationNumber : {0}")
	@ValueSource(strings = { "aaa", "-1" })
	public void testfindPersonInfoByFireStationNumber_Status_InvalidFormatArgument(String stationNumber) throws Exception {
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
		when(alertService.listPersonPhoneByStationNumber(1)).thenReturn(Mockito.<String>anyIterable());
		
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

}
