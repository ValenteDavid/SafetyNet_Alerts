package com.safetynet.safetynetalerts.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.FireStation;

@Repository
public class FireStationImpl implements FireStationDao{

	public static List<FireStation> fireStations;
	
	@Override
	public Iterable<String> findAddressByStationNumber(int station_number) {
		Set<String> setAddress = new HashSet<>();
		
		Stream<FireStation> stream = StreamSupport.stream(fireStations.spliterator(), false);
		
		stream.filter(x -> x.getStationNumber()==station_number )
		.forEach(x -> setAddress.add(x.getAddress()));
		return setAddress;
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
