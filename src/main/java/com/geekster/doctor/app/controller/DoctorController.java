package com.geekster.doctor.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.doctor.app.entity.AppointmentEntity;
import com.geekster.doctor.app.entity.DoctorEntity;
import com.geekster.doctor.app.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@PostMapping()
	void addDoctors(@RequestBody DoctorEntity doc) {
		doctorService.addDoc(doc);
	}

	@GetMapping("{docId}/appointments")
	ResponseEntity<List<AppointmentEntity>> geMyAppointments(@PathVariable Long docId) {

		List<AppointmentEntity> myAppointments = null;
		HttpStatus status;
		try {
			myAppointments = doctorService.getMyAppointments(docId);

			if (myAppointments.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			} else {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			System.out.println("The doc Id is not valid");
			status = HttpStatus.BAD_REQUEST;

		}

		return new ResponseEntity<List<AppointmentEntity>>(myAppointments, status);

	}

}
