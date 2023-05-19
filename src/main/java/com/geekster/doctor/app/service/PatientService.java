package com.geekster.doctor.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.geekster.doctor.app.entity.AppointmentKey;
import com.geekster.doctor.app.entity.AuthenticationEntity;
import com.geekster.doctor.app.entity.DoctorEntity;
import com.geekster.doctor.app.entity.PatientEntity;
import com.geekster.doctor.app.model.SignIn;
import com.geekster.doctor.app.repository.PatientRepository;
import com.geekster.doctor.app.response.ApplicationResponse;
import com.geekster.doctor.app.response.SignInResponse;

import jakarta.xml.bind.DatatypeConverter;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	DoctorService doctorService;

	public ApplicationResponse signUp(PatientEntity patient) {

		PatientEntity patientExists = patientRepository.findFirstByEmail(patient.getEmail());
		ApplicationResponse applicationResponse = new ApplicationResponse();
		if (patientExists != null) {
			applicationResponse.setMessage("Patient already exists!!!!...sign in instead");
			applicationResponse.setStatus(HttpStatus.OK);
		} else {
			String encryptedPassword = null;
			try {
				encryptedPassword = encryptPassword(patient.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			}

			PatientEntity newPatient = new PatientEntity();
			newPatient = newPatient.toBuilder().firstName(patient.getFirstName()).lastName(patient.getLastName())
					.email(patient.getEmail()).phoneNumber(patient.getPhoneNumber()).password(encryptedPassword)
					.build();

			patientRepository.save(newPatient);

			AuthenticationEntity authentication = new AuthenticationEntity();
			authentication = authentication.toBuilder().createdDate(LocalDateTime.now())
					.token(UUID.randomUUID().toString()).patient(newPatient).build();

			authenticationService.saveToken(authentication);
			applicationResponse.setMessage("Patient registerd successfully");
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

		PatientEntity patient = patientRepository.findFirstByEmail(signInInput.getEmail());
		SignInResponse signInResponse = new SignInResponse();
		if (patient == null) {
			signInResponse.setMessage("Patient not registered...please sign up");
			signInResponse.setToken(null);
		} else {
			String encryptedPassword = null;
			try {
				encryptedPassword = encryptPassword(signInInput.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			}

			boolean isPasswordValid = encryptedPassword.equals(patient.getPassword());

			if (!isPasswordValid) {
				signInResponse.setMessage("Patient password invalid");
				signInResponse.setToken(null);
			} else {
				AuthenticationEntity token = authenticationService.getToken(patient);
				signInResponse.setMessage("Authentication Successfull !!!");
				signInResponse.setToken(token.getToken());
			}

		}

		return signInResponse;

	}

	public List<DoctorEntity> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	public void cancelAppointment(AppointmentKey key) {
		appointmentService.cancelAppointment(key);
	}
}
