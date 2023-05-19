package com.geekster.doctor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.doctor.app.entity.AppointmentEntity;
import com.geekster.doctor.app.service.AppointmentService;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@PostMapping()
	public void bookAppointment(@RequestBody AppointmentEntity appointment) {
		appointmentService.bookAppointment(appointment);
	}

}
