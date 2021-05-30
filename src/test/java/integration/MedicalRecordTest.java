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
import com.safetynet.safetynetalerts.model.MedicalRecord;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class MedicalRecordTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSave() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord("Firstname", "Lastname", null,
				new String[] { "aznol:350mg", "hydrapermazol:100mg" }, new String[] { "nillacilan" });
		medicalRecord.setBirthdate("03/06/1984");
		String json = new ObjectMapper().writeValueAsString(medicalRecord);

		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testDelete() {
	}

}
