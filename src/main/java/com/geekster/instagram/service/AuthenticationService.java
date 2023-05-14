package com.geekster.instagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.instagram.entity.AuthenticationEntity;
import com.geekster.instagram.entity.UserEntity;
import com.geekster.instagram.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	@Autowired
	AuthenticationRepository authenticationRepository;

	public void saveToken(AuthenticationEntity authenticationEntity) {
		authenticationRepository.save(authenticationEntity);
	}

	public AuthenticationEntity getToken(UserEntity user) {
		return authenticationRepository.findByUser(user);

	}
	
	 public boolean authenticate(String userEmail, String token) {

		 AuthenticationEntity authToken = authenticationRepository.findFirstByToken(token);
		 boolean isAuthenticated = false;
		 if(authToken != null) {
			 String expectedEmail = authToken.getUser().getEmail();
			 if(expectedEmail.equals(userEmail)) {
				 isAuthenticated = true;
			 }
		 }
         
         return isAuthenticated;

    }

}
