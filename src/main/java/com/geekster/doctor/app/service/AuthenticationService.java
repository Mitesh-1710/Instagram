package com.geekster.doctor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.doctor.app.entity.AuthenticationEntity;
import com.geekster.doctor.app.entity.PatientEntity;
import com.geekster.doctor.app.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	@Autowired
	AuthenticationRepository authenticationRepository;

	public void saveToken(AuthenticationEntity authenticationEntity) {
		authenticationRepository.save(authenticationEntity);
	}

	public AuthenticationEntity getToken(PatientEntity user) {
		return authenticationRepository.findByUser(user);

	}
	
	 public boolean authenticate(String userEmail, String token) {
		 AuthenticationEntity authToken = authenticationRepository.findFirstByToken(token);
		 boolean isAuthenticated = false;
		 if(authToken != null) {
			 String expectedEmail = authToken.getPatient().getEmail();
			 if(expectedEmail.equals(userEmail)) {
				 isAuthenticated = true;
			 }
		 }
         return isAuthenticated;
    }

}
