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
import com.safetynet.safetynetalerts.model.MedicalRecord;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@AutoConfigureMockMvc
class MedicalRecordTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void testGet() throws Exception {
		String firstName = "John";
		String lastName = "Boyd";

		mockMvc.perform(get("/medicalRecord/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(firstName))
				.andExpect(jsonPath("$.lastName").value(lastName))
				.andExpect(jsonPath("$.birthdate").value("03/06/1984"))
				.andExpect(jsonPath("$.medications[0]").value( "aznol:350mg"))
				.andExpect(jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
				.andExpect(jsonPath("$.allergies[0]").value("nillacilan"));
	}

	@Test
	@Order(2) 
	void testSave() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, null,
				new String[] { "aznol:350mg", "hydrapermazol:100mg" }, new String[] { "nillacilan" });
		medicalRecord.setBirthdate("03/06/1984");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);

		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord/{0}&{1}", firstName, lastName))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
		
	}

	@Test
	@Order(3) 
	void testUpdate() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";
		MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, null,
				new String[] { "aznol:500mg", "hydrapermazol:500mg" }, new String[] { "milk" });
		medicalRecord.setBirthdate("05/06/1984");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);

		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord/{0}&{1}", firstName, lastName))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Order(4) 
	void testDelete() throws Exception{
		String firstName = "Firstname";
		String lastName = "Lastname";

		mockMvc.perform(get("/medicalRecord/{0}&{1}", firstName, lastName))
		.andExpect(status().isOk());
		
		mockMvc.perform(delete("/medicalRecord/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk());
		
		mockMvc.perform(get("/medicalRecord/{0}&{1}", firstName, lastName))
		.andExpect(status().isNotFound());
	}

}
