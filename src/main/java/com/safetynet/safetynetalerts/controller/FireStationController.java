package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.controller.exceptions.NotFoundException;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;

@RestController
public class FireStationController {

	private static final Logger log = LoggerFactory.getLogger(FireStationController.class);

	private static final String path = "/firestation";

	@Autowired
	private FireStationService fireStationService;

	@GetMapping(path + "/{address}&{stationNumber}")
	public FireStation get(@PathVariable String address, @PathVariable int stationNumber) {
		log.info("GET {} called", path + "/{address}&{stationNumber}");
		log.debug("Paramameter address : {}, stationNumber : {}", address, stationNumber);
		
		FireStation firestationFound = fireStationService.findByAddressANDStationNumber(address, stationNumber);
		log.debug("firestationFound : {}", firestationFound);
		
		if (firestationFound == null) {
			throw new NotFoundException(
					"No one found at this adresse : " + address + " stationNumber : " + stationNumber);
		}

		log.info("GET {} response : {}", path + "/{address}&{stationNumber}", firestationFound);
		return firestationFound;
	}

	@PostMapping(path)
	public ResponseEntity<Void> save(@Valid @RequestBody FireStation fireStation) {
		log.info("POST {} called", path);
		log.debug("Paramameter fireStation : {}", fireStation);
		
		FireStation firestationSave = fireStationService.save(fireStation);
		log.debug("firestationSave : {}", firestationSave);

		if (firestationSave == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{address}&{stationNumber}")
				.buildAndExpand(firestationSave.getAddress(), fireStation.getStationNumber())
				.toUri();

		log.info("POST {} end", path);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path)
	public FireStation update(@Valid @RequestBody FireStation fireStation) {
		log.info("PUT {} called", path);
		log.debug("Paramameter fireStation : {}", fireStation);
		
		FireStation firestationUpdate = fireStationService.update(fireStation);
		log.debug("personUpdate : {}", firestationUpdate);
		
		if (firestationUpdate == null) {
			throw new NotFoundException(
					"No found to update this fireStation : " + fireStation);
		}
		
		log.info("PUT {} response : {}", path, firestationUpdate);
		return firestationUpdate;
	}

	@DeleteMapping(path + "/{address}&{stationNumber}")
	public void delete(@PathVariable String address, @PathVariable int stationNumber) {
		log.info("DELETE {} called", path + "/{address}&{stationNumber}");
		log.debug("Paramameter address : {}, stationNumber : {}", address, stationNumber);
		
		if (!fireStationService.delete(address, stationNumber)) {
			throw new NotFoundException(
					"No one found at this adresse : " + address + " stationNumber : " + stationNumber);
		}
		
		log.info("DELETE {} end", path);
	}

}
