package com.safetynet.safetynetalerts;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.DataFile;
import com.safetynet.safetynetalerts.repository.FireStationImpl;
import com.safetynet.safetynetalerts.repository.MedicalRecordImpl;
import com.safetynet.safetynetalerts.repository.PersonDaoImpl;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner runner() {
		return args -> {
			File fileUse = new File("src/main/resources/data.json");
			File fileLoad = new File("src/main/resources/data-original.json");
			if (fileUse.exists()) {
				fileUse.delete();
			}
			Files.copy(fileLoad.toPath(), fileUse.toPath());
		};
	}
	
	@Bean
	CommandLineRunner runner_start() {
		return args -> {
			DataFile.loadFile();
		};
		
	}
}
