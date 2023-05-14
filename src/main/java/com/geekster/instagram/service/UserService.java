package com.geekster.instagram.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.geekster.instagram.entity.AuthenticationEntity;
import com.geekster.instagram.entity.UserEntity;
import com.geekster.instagram.model.SignIn;
import com.geekster.instagram.model.User;
import com.geekster.instagram.repository.UserRepository;
import com.geekster.instagram.response.ApplicationResponse;
import com.geekster.instagram.response.SignInResponse;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationService authenticationService;

	public ApplicationResponse signUp(User user) {

		UserEntity userExists = userRepository.findFirstByEmail(user.getEmail());
		ApplicationResponse applicationResponse = new ApplicationResponse();
		if (userExists != null) {
			applicationResponse.setMessage("User already exists!!!!...sign in instead");
			applicationResponse.setStatus(HttpStatus.OK);
		} else {
			String encryptedPassword = null;
			try {
				encryptedPassword = encryptPassword(user.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			}

			UserEntity newUser = new UserEntity();
			newUser = newUser.toBuilder().firstName(user.getFirstName()).lastName(user.getLastName()).age(user.getAge())
					.email(user.getEmail()).phoneNumber(user.getPhoneNumber()).password(encryptedPassword).build();

			userRepository.save(newUser);

			AuthenticationEntity authentication = new AuthenticationEntity();
			authentication = authentication.toBuilder().createdDate(LocalDateTime.now())
					.token(UUID.randomUUID().toString()).user(newUser).build();

			authenticationService.saveToken(authentication);
			applicationResponse.setMessage("User registerd successfully");
			applicationResponse.setStatus(HttpStatus.OK);
		}

		return applicationResponse;

	}

	private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(userPassword.getBytes());
		byte[] digested = md5.digest();
		return DatatypeConverter.printHexBinary(digested);
	}

	public SignInResponse signIn(SignIn signInInput) {

		UserEntity user = userRepository.findFirstByEmail(signInInput.getEmail());
		SignInResponse signInResponse = new SignInResponse();
		if (user == null) {
			signInResponse.setMessage("User not registered...please sign up");
			signInResponse.setToken(null);
		} else {
			String encryptedPassword = null;
			try {
				encryptedPassword = encryptPassword(signInInput.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			}

			boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

			if (!isPasswordValid) {
				signInResponse.setMessage("User password invalid");
				signInResponse.setToken(null);
			} else {
				AuthenticationEntity token = authenticationService.getToken(user);
				signInResponse.setMessage("Authentication Successfull !!!");
				signInResponse.setToken(token.getToken());
			}

		}

		return signInResponse;

	}

	public void updateUser(@Valid User user, Long userId) {

		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if (userEntity.isPresent()) {
			UserEntity updatedUser = userEntity.get();
			updatedUser = updatedUser.toBuilder().age(user.getAge()).firstName(user.getFirstName())
					.lastName(user.getLastName()).email(user.getEmail()).phoneNumber(user.getPhoneNumber()).build();
			userRepository.save(updatedUser);
		}
	}

}
