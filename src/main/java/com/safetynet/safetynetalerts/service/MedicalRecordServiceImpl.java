package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordDao;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@Override
	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName) {
		return medicalRecordDao.findByFirstNameANDLastName(firstName, lastName);
	}

	@Override
	public List<MedicalRecord> findAll() {
		return null;
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}

	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) {
		return null;
	}

	@Override
	public void delete(MedicalRecord medicalRecord) {

	}

}
