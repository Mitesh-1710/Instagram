package com.geekster.doctor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.doctor.app.entity.AppointmentEntity;
import com.geekster.doctor.app.entity.DoctorEntity;
import com.geekster.doctor.app.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public void addDoc(DoctorEntity doc) {
		doctorRepository.save(doc);
	}

	public List<DoctorEntity> getAllDoctors() {
		List<DoctorEntity> allDoctors = doctorRepository.findAll();
		return allDoctors;

	}

	public List<AppointmentEntity> getMyAppointments(Long docId) {
		DoctorEntity myDoc = doctorRepository.findByDoctorId(docId);
		if (myDoc == null) {
			throw new IllegalStateException("The doctor does not exist");
		}
		return myDoc.getAppointments();
	}
}
