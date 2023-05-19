package com.geekster.doctor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekster.doctor.app.entity.AppointmentEntity;
import com.geekster.doctor.app.entity.AppointmentKey;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, AppointmentKey> {

	public String findByIdAppId(Long id);
}
