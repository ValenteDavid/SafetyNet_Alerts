package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;

public interface FireStationDao {

	public List<String> findAddressByStationNumber(int station_number);

	public Integer findStationNumberByAddress(String Address);
	
	public List<FireStation> findAll();

	public FireStation save(FireStation fireStation);

	public FireStation update(FireStation fireStation);

	public boolean delete(FireStation fireStation);

	public FireStation findByAddressANDStationNumber(String adresse, int stationNumber);

}
