package com.safetynet.safetynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	void testGet() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		when(personService.findByFirstNameANDLastName(firstName, lastName)).thenReturn(new Person(firstName, lastName));

		mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk());
	}

	void testGet_thenReturnNull() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		when(personService.findByFirstNameANDLastName(firstName, lastName)).thenReturn(null);

		mockMvc.perform(get("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isNotFound());
	}

	void testAdd() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		Person person = new Person(firstName, lastName, "Address", "City", (long) 00000, "Phone",
				"email@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		when(personService.findByFirstNameANDLastName(firstName, lastName)).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated())
				.andReturn();

		Pattern uriExpect = Pattern.compile(".*/person/" + firstName + "&" + lastName);
		assertTrue(uriExpect.matcher(mvcResult.getResponse().getRedirectedUrl()).matches());

	}

	void testUpdate() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		Person person = new Person(firstName, lastName, "Address", "City", (long) 00000, "Phone",
				"email@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		when(personService.update(any())).thenReturn(person);

		mockMvc.perform(put("/person/{0}&{1}", firstName, lastName)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	void testUpdate_thenReturnNull() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		Person person = new Person(firstName, lastName, "Address", "City", (long) 00000, "Phone",
				"email@email.com");
		String json = new ObjectMapper().writeValueAsString(person);

		when(personService.update(any())).thenReturn(null);

		mockMvc.perform(put("/person/{0}&{1}", firstName, lastName)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isNotFound());
	}

	void testDelete() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		when(personService.delete(firstName, lastName)).thenReturn(true);

		mockMvc.perform(delete("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isOk());
	}

	void testDelete_thenReturnFalse() throws Exception {
		String firstName = "Firstname";
		String lastName = "Lastname";

		when(personService.delete(firstName, lastName)).thenReturn(false);

		mockMvc.perform(delete("/person/{0}&{1}", firstName, lastName))
				.andExpect(status().isNotFound());
	}

}
