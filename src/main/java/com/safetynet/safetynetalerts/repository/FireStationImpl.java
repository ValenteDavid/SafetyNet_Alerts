package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.FireStation;

@Repository
public class FireStationImpl implements FireStationDao{

	public static List<FireStation> fireStations;
	
	@Override
	public Iterable<String> findAllByStationNumber(int station_number) {
		return null;
	}

	@Override
	public Iterable<FireStation> findAll() {
		return null;
	}

	@Override
	public FireStation save(FireStation fireStation) {
		return null;
	}

	@Override
	public FireStation update(FireStation fireStation) {
		return null;
	}

	@Override
	public void delete(FireStation fireStation) {
		
	}

}
