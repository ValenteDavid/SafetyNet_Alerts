package integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class PersonTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGet() throws Exception {
		Person person = new Person("Firstname", "Lastname", "Address", "City", (long) 00000, "Phone", "Email");
		String json = new ObjectMapper().writeValueAsString(person);

		mockMvc.perform(get("/person/John&Boyd"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.lastName").value("Boyd"))
				.andExpect(jsonPath("$.address").value("1509 Culver St"))
				.andExpect(jsonPath("$.city").value("Culver"))
				.andExpect(jsonPath("$.zip").value(97451))
				.andExpect(jsonPath("$.phone").value("841-874-6512"))
				.andExpect(jsonPath("$.email").value("jaboyd@email.com"))
				;
	}

	@Test
	void testSave() throws Exception {
		Person person = new Person("Firstname", "Lastname", "Address", "City", (long) 00000, "Phone", "email@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		mockMvc.perform(post("/person")
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
