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
	public FireStation save(FireStation fireStation) {
		return fireStationDao.save(fireStation);
	}

	@Override
	public FireStation update(FireStation fireStation) {
		return fireStationDao.update(fireStation);
	}

	@Override
	public boolean delete(String adresse, int stationNumber) {
		return fireStationDao.delete(
				fireStationDao.findByAddressANDStationNumber(adresse, stationNumber));
	}

	@Override
	public List<String> findAddressByStationNumber(int stationNumber) {
		return fireStationDao.findAddressByStationNumber(stationNumber);
	}

	@Override
	public Integer findStationNumberByAddress(String Address) {
		return fireStationDao.findStationNumberByAddress(Address);
	}

	public FireStation findByAddressANDStationNumber(String adresse, int stationNumber) {
		return fireStationDao.findByAddressANDStationNumber(adresse, stationNumber);
	}

}
