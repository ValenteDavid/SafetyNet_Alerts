package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordDao {

	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName);

	public List<MedicalRecord> findAll();

	public MedicalRecord save(MedicalRecord medicalRecord);

	public MedicalRecord update(MedicalRecord medicalRecord);

	public void delete(MedicalRecord medicalRecord);
}
