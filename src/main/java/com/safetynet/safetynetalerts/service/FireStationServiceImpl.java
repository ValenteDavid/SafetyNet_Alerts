package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationDao;

@Service
public class FireStationServiceImpl implements FireStationService {

	@Autowired
	private FireStationDao fireStationDao;

	@Override
	public FireStation add(FireStation fireStation) {
		return null;
	}

	@Override
	public FireStation update(FireStation fireStation) {
		return null;
	}

	@Override
	public void delete(String adresse, int stationNumber) {
	}

	@Override
	public List<String> findAddressByStationNumber(int stationNumber) {
		return fireStationDao.findAddressByStationNumber(stationNumber);
	}

	@Override
	public Integer findStationNumberByAddress(String Address) {
		return fireStationDao.findStationNumberByAddress(Address);
	}

}
