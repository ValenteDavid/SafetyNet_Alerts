package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordService {

	public MedicalRecord findByFirstNameANDLastName(String firstName,String lastName);
	public Iterable<MedicalRecord> findAll();
	public MedicalRecord save(MedicalRecord medicalRecord);
	public MedicalRecord update (MedicalRecord medicalRecord);
	public void delete(MedicalRecord medicalRecord);
}
