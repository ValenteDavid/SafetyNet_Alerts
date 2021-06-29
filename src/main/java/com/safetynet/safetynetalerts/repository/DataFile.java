package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

public class DataFile {
	
	private static final Logger log = LoggerFactory.getLogger(DataFile.class);

	private static String filepathuse;

	public static String getFilepathuse() {
		return filepathuse;
	}

	public static void setFilepathuse(String filepathuse) {
		DataFile.filepathuse = filepathuse;
	}

	public static void loadFile() {
		try {
			File fileUse = new File(filepathuse);

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
			log.error("Error load file", e);
		}
	}

	public static void saveFile() {
		try {
			File fileUse = new File(filepathuse);

			FileData fileDTO = new FileData(
					PersonDaoImpl.persons,
					FireStationImpl.fireStations,
					MedicalRecordImpl.medicalRecords);

			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(fileUse, fileDTO);
		} catch (IOException e) {
			log.error("Error save file", e);
		}
	}

}
