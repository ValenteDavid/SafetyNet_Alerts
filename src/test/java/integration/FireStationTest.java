package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.FireStation;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@AutoConfigureMockMvc
class FireStationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1) 
	void testGet() throws Exception {
		String address = "29 15th St";
		String stationNumber = "2";

		mockMvc.perform(get("/firestation/{0}&{1}", address, stationNumber))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.address").value(address))
				.andExpect(jsonPath("$.station").value(stationNumber));
	}

	@Test
	@Order(2) 
	void testSave() throws Exception {
		String address = "Address";
		String stationNumber = "777";
		FireStation fireStation = new FireStation( address, Integer.parseInt(stationNumber));
		String json = new ObjectMapper().writeValueAsString(fireStation);

		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		
		MvcResult mvcResult = mockMvc.perform(get("/firestation/{0}&{1}", address, stationNumber))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Order(3) 
	void testUpdate() throws Exception {
		String address = "Address";
		String stationNumber = "888";
		FireStation fireStation = new FireStation(address, Integer.parseInt(stationNumber));
		String json = new ObjectMapper().writeValueAsString(fireStation);

		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		MvcResult mvcResult = mockMvc.perform(get("/firestation/{0}&{1}", address, stationNumber))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Order(4) 
	void testDelete() throws Exception {
		String address = "Address";
		String stationNumber = "888";

		mockMvc.perform(get("/firestation/{0}&{1}", address, stationNumber))
				.andExpect(status().isOk());

		mockMvc.perform(delete("/firestation/{0}&{1}", address, stationNumber))
				.andExpect(status().isOk());

		mockMvc.perform(get("/firestation/{0}&{1}", address, stationNumber))
				.andExpect(status().isNotFound());
	}

}
