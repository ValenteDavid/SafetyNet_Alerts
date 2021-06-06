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
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
class PersonTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void testGet() throws Exception {
		String firstName = "John";
		String lastName = "Boyd";

		mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(firstName))
				.andExpect(jsonPath("$.lastName").value(lastName))
				.andExpect(jsonPath("$.address").value("1509 Culver St"))
				.andExpect(jsonPath("$.city").value("Culver"))
				.andExpect(jsonPath("$.zip").value(97451))
				.andExpect(jsonPath("$.phone").value("841-874-6512"))
				.andExpect(jsonPath("$.email").value("jaboyd@email.com"));
	}

	@Test
	@Order(2)
	void testSave() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		Person person = new Person(firstName, lastName, "Address", "City", (long) 00000, "Phone", "email@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());

		MvcResult mvcResult = mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Order(3)
	void testUpdate() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		Person person = new Person(firstName, lastName, "AddressAddress", "CityCity", (long) 00000, "Phonee",
				"emailll@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		MvcResult mvcResult = mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andReturn();

		assertEquals(json, mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Order(4)
	void testDelete() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk());

		mockMvc.perform(delete("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk());

		mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isNotFound());

	}

}
