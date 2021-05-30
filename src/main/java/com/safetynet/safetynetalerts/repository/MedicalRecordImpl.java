package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordImpl implements MedicalRecordDao {

	public static List<MedicalRecord> medicalRecords;

	@Override
	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName) {
		MedicalRecord medicalRecord = medicalRecords.stream()
				.filter(x -> firstName.equals(x.getFirstName()))
				.filter(x -> lastName.equals(x.getLastName()))
				.findFirst()
				.get();
		return medicalRecord;
	}

	@Override
	public List<MedicalRecord> findAll() {
		return null;
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		return medicalRecords.add(medicalRecord)?medicalRecord:null;
	}

	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) {
		return null;
	}

	@Override
	public void delete(MedicalRecord medicalRecord) {

	}

}
