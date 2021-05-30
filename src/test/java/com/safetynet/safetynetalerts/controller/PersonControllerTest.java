package com.safetynet.safetynetalerts.controller;

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
import com.safetynet.safetynetalerts.model.Person;
@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testSave() throws Exception {
		Person person = new Person("Firstname", "Lastname", "Address","City",(long) 00000,"Phone","email@email.com");
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
