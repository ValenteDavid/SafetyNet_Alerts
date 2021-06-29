package com.safetynet.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
class AlertServiceImplTest {

	@Autowired
	private AlertService alertServiceImpl;

	@MockBean
	private MedicalRecordServiceImpl medicalRecordService;
	
	@Test
	void testAgeOfPersonByBirthdate() {
		int ageExpect = 10;

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -ageExpect);
		Date date = c.getTime();

		int ageResult = alertServiceImpl.ageOfPersonByBirthdate(date);

		assertEquals(ageExpect, ageResult);

	}

	@Test
	void testAgeOfPersonByPerson() {
		int ageExpect = 10;
		Person person = new Person("Bob", "Bob");
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -ageExpect);
		Date date = c.getTime();
		
		when((medicalRecordService.findByFirstNameANDLastName("Bob","Bob"))).thenReturn(new MedicalRecord(null, null, date, null, null));
		
		int ageResult = alertServiceImpl.ageOfPersonByPerson(person);

		assertEquals(ageExpect, ageResult);
	}

}
