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
		FireStation fireStation = fireStations.stream()
				.filter(x -> x.getAddress().equals(address))
				.findFirst().orElse(null);
		if (fireStation == null) {
			return null;
		}
		Integer stationNumber = fireStation.getStationNumber();
		return stationNumber;
	}
	
	@Override
	public List<FireStation> findAll() {
		return fireStations;
	}

	@Override
	public FireStation save(FireStation fireStation) {
		FireStation firestationAdd = fireStations.add(fireStation) ? fireStation : null;
		DataFile.saveFile();
		return firestationAdd;
	}

	@Override
	public FireStation update(FireStation fireStation) {
		FireStation fireStationUpdate = fireStations.stream()
				.filter(x -> fireStation.getAddress().equals(x.getAddress()))
				.findFirst().orElse(null);

		if (fireStationUpdate == null) {
			return null;
		}
		fireStations.remove(fireStationUpdate);
		fireStations.add(fireStation);
		DataFile.saveFile();
		return fireStation;
	}

	@Override
	public boolean delete(FireStation fireStation) {
		boolean response = fireStation == null ? false : fireStations.remove(fireStation);
		DataFile.saveFile();
		return response;
	}

	@Override
	public FireStation findByAddressANDStationNumber(String adresse, int stationNumber) {
		FireStation fireStationFound = fireStations.stream()
				.filter(x -> adresse.equals(x.getAddress()))
				.filter(x -> stationNumber == x.getStationNumber())
				.findFirst().orElse(null);
		return fireStationFound;
	}

}
