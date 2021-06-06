package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordImpl implements MedicalRecordDao {

	public static List<MedicalRecord> medicalRecords;

	@Override
	public MedicalRecord findByFirstNameANDLastName(String firstName, String lastName) {
		MedicalRecord medicalRecord;
			medicalRecord = medicalRecords.stream()
				.filter(x -> firstName.equals(x.getFirstName()))
				.filter(x -> lastName.equals(x.getLastName()))
				.findFirst().orElse(null);
		return medicalRecord;
	}
	
	@Override
	public List<MedicalRecord> findAll() {
		return medicalRecords;
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		return medicalRecords.add(medicalRecord)?medicalRecord:null;
	}

	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordUpadate;
		medicalRecordUpadate = medicalRecords.stream()
				.filter(x -> medicalRecord.getFirstName().equals(x.getFirstName()))
				.filter(x -> medicalRecord.getLastName().equals(x.getLastName()))
				.findFirst().orElse(null);
		
		if (medicalRecordUpadate !=null) {
			medicalRecords.remove(medicalRecordUpadate);
			medicalRecords.add(medicalRecord);
			DataFile.saveFile();
			return medicalRecord;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean delete(MedicalRecord medicalRecord) {
		boolean response = medicalRecord==null?false:medicalRecords.remove(medicalRecord);
		DataFile.saveFile();
		return response;
	}

}
