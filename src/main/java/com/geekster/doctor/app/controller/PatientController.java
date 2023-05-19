package com.geekster.doctor.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.doctor.app.entity.AppointmentKey;
import com.geekster.doctor.app.entity.DoctorEntity;
import com.geekster.doctor.app.entity.PatientEntity;
import com.geekster.doctor.app.model.SignIn;
import com.geekster.doctor.app.response.ApplicationResponse;
import com.geekster.doctor.app.response.SignInResponse;
import com.geekster.doctor.app.service.AuthenticationService;
import com.geekster.doctor.app.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@Autowired
	AuthenticationService authService;

	@PostMapping("/signup")
	public ApplicationResponse signup(@Valid @RequestBody PatientEntity patient) {
		return patientService.signUp(patient);
	}

	@PostMapping("/signin")
	public SignInResponse signIn(@RequestBody SignIn signIn) {
		return patientService.signIn(signIn);
	}

	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorEntity>> getAllDoctors(@RequestParam String userEmail,
			@RequestParam String token) {
		HttpStatus status;
		List<DoctorEntity> allDoctors = null;

		if (authService.authenticate(userEmail, token)) {

			allDoctors = patientService.getAllDoctors();
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<List<DoctorEntity>>(allDoctors, status);
	}

	@DeleteMapping("appointment")
	ResponseEntity<Void> cancelAppointment(@RequestParam String userEmail, @RequestParam String token,
			@RequestBody AppointmentKey key) {

		HttpStatus status;
		if (authService.authenticate(userEmail, token)) {
			patientService.cancelAppointment(key);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.FORBIDDEN;
		}

		return new ResponseEntity<Void>(status);

	}

}
