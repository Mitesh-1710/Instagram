package com.geekster.doctor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekster.doctor.app.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

	public PatientEntity findFirstByPatientEmail(String userEmail);
}
