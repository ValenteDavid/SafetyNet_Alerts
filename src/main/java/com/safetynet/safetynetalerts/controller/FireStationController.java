package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import javax.validation.Valid;

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

	private static final String path = "/firestation";

	@Autowired
	private FireStationService fireStationService;

	@GetMapping(path + "/{address}&{stationNumber}")
	public FireStation get(@PathVariable String address, @PathVariable int stationNumber) {
		FireStation firestationFound = fireStationService.findByAddressANDStationNumber(address, stationNumber);

		if (firestationFound == null) {
			throw new NotFoundException(
					"No one found at this adresse : " + address + " stationNumber : " + stationNumber);
		}

		return firestationFound;
	}

	@PostMapping(path)
	public ResponseEntity<Void> save(@Valid @RequestBody FireStation fireStation) {
		FireStation firestationSave = fireStationService.save(fireStation);

		if (firestationSave == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{address}&{stationNumber}")
				.buildAndExpand(firestationSave.getAddress(), fireStation.getStationNumber())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path)
	public FireStation update(@Valid @RequestBody FireStation fireStation) {
		FireStation firestationSave = fireStationService.update(fireStation);
		
		if (firestationSave == null) {
			throw new NotFoundException(
					"No found to update this fireStation : " + fireStation);
		}
		return firestationSave;
	}

	@DeleteMapping(path + "/{address}&{stationNumber}")
	public void delete(@PathVariable String address, @PathVariable int stationNumber) {
		if (!fireStationService.delete(address, stationNumber)) {
			throw new NotFoundException(
					"No one found at this adresse : " + address + " stationNumber : " + stationNumber);
		}
	}

}
