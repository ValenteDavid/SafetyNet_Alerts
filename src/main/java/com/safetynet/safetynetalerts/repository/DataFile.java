package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@PropertySource("classpath:app.data")
@ConfigurationProperties
public class DataFile {
	
	private static String filepath;

	public static void loadFile() {
		try {
			File fileUse = new File("src/main/resources/data.json");

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
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public static void saveFile() {
		
	}

}
