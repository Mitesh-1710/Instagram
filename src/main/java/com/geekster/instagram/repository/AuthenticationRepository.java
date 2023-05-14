package com.geekster.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.instagram.entity.AuthenticationEntity;
import com.geekster.instagram.entity.UserEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

	AuthenticationEntity findByUser(UserEntity user);
	
	AuthenticationEntity findFirstByToken(String token);
	
}
