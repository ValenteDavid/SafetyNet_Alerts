package com.safetynet.safetynetalerts;

import java.io.File;
import java.nio.file.Files;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.safetynet.safetynetalerts.repository.DataFile;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner runner(Environment env) {
		return args -> {
			File fileUse = new File(env.getProperty("datafile.filepath.use"));
			File fileLoad = new File(env.getProperty("datafile.filepath.source"));
			if (fileUse.exists()) {
				fileUse.delete();
			}
			Files.copy(fileLoad.toPath(), fileUse.toPath());
		};
	}
	
	@Bean
	CommandLineRunner runner_start(Environment env) {
		return args -> {
			DataFile.setFilepathuse(env.getProperty("datafile.filepath.use"));
			DataFile.loadFile();
		};
		
	}

}
