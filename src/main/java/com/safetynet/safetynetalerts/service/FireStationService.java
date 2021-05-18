package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;

public interface FireStationService {

	public FireStation add(FireStation fireStation);

	public FireStation update(FireStation fireStation);

	public void delete(String adresse, int stationNumber);

	public List<String> findAddressByStationNumber(int stationNumber);

	public Integer findStationNumberByAddress(String Address);

}
