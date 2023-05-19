package com.geekster.doctor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekster.doctor.app.entity.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

	public DoctorEntity findByDoctorId(Long docId);
}
