package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;

public interface FireStationService {

	public FireStation add(FireStation fireStation);
	public FireStation update(FireStation fireStation);
	public void delete(String adresse,int stationNumber);
	
	public Iterable<String> findAddressByStationNumber(int stationNumber);
	
}
