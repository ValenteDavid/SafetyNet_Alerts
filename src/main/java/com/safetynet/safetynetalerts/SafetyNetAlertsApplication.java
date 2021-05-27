package com.safetynet.safetynetalerts;

import java.io.File;
import java.nio.file.Files;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.safetynet.safetynetalerts.repository.DataFile;

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
			File fileLoad = new File("src/main/resources/data-test.json");
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
