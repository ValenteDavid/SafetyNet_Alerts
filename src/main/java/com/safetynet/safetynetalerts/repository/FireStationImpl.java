package com.safetynet.safetynetalerts.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.FireStation;

@Repository
public class FireStationImpl implements FireStationDao {

	public static List<FireStation> fireStations;

	@Override
	public List<String> findAddressByStationNumber(int station_number) {

		List<String> listAddress = fireStations.stream()
				.filter(fireStation -> fireStation.getStationNumber() == station_number)
				.map(fireStation -> fireStation.getAddress())
				.collect(Collectors.toList());

		return listAddress;
	}

	@Override
	public Integer findStationNumberByAddress(String address) {

		Integer stationNumber = fireStations.stream()
				.filter(x -> x.getAddress().equals(address))
				.findFirst()
				.get().getStationNumber();

		return stationNumber;
	}

	@Override
	public List<FireStation> findAll() {
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
