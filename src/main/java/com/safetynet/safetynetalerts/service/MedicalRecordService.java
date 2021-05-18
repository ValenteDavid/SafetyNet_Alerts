package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordService {

	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName);

	public List<MedicalRecord> findAll();

	public MedicalRecord save(MedicalRecord medicalRecord);

	public MedicalRecord update(MedicalRecord medicalRecord);

	public void delete(MedicalRecord medicalRecord);
}
