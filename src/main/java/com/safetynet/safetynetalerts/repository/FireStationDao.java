package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.FireStation;

public interface FireStationDao {
	
	public Iterable<String> findAddressByStationNumber(int station_number);
	public Iterable<FireStation> findAll();
	public FireStation save(FireStation fireStation);
	public FireStation update (FireStation fireStation);
	public void delete(FireStation fireStation);

}
