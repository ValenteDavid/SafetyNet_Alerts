package com.safetynet.safetynetalerts;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.FireStationImpl;
import com.safetynet.safetynetalerts.repository.MedicalRecordImpl;
import com.safetynet.safetynetalerts.repository.PersonDaoImpl;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			File fileLoad = new File("src/main/resources/data.json");
			File fileUse = new File("src/main/resources/data-test.json");
			if (fileUse.exists()) {fileUse.delete();}
			Files.copy(fileLoad.toPath(), fileUse.toPath());
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(fileUse);
			ArrayNode arrayNode;
			Iterator<JsonNode> itr;

			arrayNode = (ArrayNode) jsonNode.get("persons");
			itr = arrayNode.elements();
			List<Person> persons = mapper.convertValue(itr, new TypeReference<List<Person>>() {
			});
			PersonDaoImpl.persons = persons;

			arrayNode = (ArrayNode) jsonNode.get("firestations");
			itr = arrayNode.elements();
			List<FireStation> fireStations = mapper.convertValue(itr, new TypeReference<List<FireStation>>() {
			});
			FireStationImpl.fireStations = fireStations;

			arrayNode = (ArrayNode) jsonNode.get("medicalrecords");
			itr = arrayNode.elements();
			List<MedicalRecord> medicalRecords = mapper.convertValue(itr, new TypeReference<List<MedicalRecord>>() {
			});
			MedicalRecordImpl.medicalRecords = medicalRecords;
		};
	}
}
