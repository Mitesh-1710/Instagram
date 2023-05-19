package com.geekster.doctor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.doctor.app.entity.AuthenticationEntity;
import com.geekster.doctor.app.entity.PatientEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

	public AuthenticationEntity findByUser(PatientEntity user);

	public AuthenticationEntity findFirstByToken(String token);

}
