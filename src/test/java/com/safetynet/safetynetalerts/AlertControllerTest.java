package com.safetynet.safetynetalerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.controller.AlertController;
import com.safetynet.safetynetalerts.service.AlertService;

@WebMvcTest(controllers = AlertController.class)
public class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlertService alertService;

	@Test
	public void testfindPersonInfoByFireStationNumber_Status_ValidArgument() throws Exception {
		String param = "1";
		mockMvc.perform(get("/firestation")
				.param("stationNumber", param))
				.andExpect(status().isOk());
	}

	@ParameterizedTest(name = "param : {0}")
	@ValueSource(strings = { "aaa", "-1" })
	public void testfindPersonInfoByFireStationNumber_Status_InvalidFormatArgument(String param) throws Exception {
		mockMvc.perform(get("/firestation")
				.param("stationNumber", param))
				.andExpect(status().isBadRequest());
	}

}
