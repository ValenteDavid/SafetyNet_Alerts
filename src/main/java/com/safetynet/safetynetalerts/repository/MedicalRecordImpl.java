package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordImpl implements MedicalRecordDao {

	public static List<MedicalRecord> medicalRecords;
	
	@Override
	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName) {
		return null;
	}

	@Override
	public Iterable<MedicalRecord> findAll() {
		return null;
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		return null;
	}

	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) {
		return null;
	}

	@Override
	public void delete(MedicalRecord medicalRecord) {
		
	}

}
