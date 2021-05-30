package integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.FireStation;
@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class FireStationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGet() throws Exception{
	}
	
	@Test
	void testSave() throws Exception {
		FireStation fireStation = new FireStation("Address", 777);
		String json = new ObjectMapper().writeValueAsString(fireStation);
		
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	void testUpdate() throws Exception {
	}

	@Test
	void testDelete() throws Exception {
	}

}
