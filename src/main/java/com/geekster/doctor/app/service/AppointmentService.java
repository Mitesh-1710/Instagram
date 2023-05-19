package com.geekster.doctor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.doctor.app.entity.AppointmentEntity;
import com.geekster.doctor.app.entity.AppointmentKey;
import com.geekster.doctor.app.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;

	public void bookAppointment(AppointmentEntity appointment) {
		appointmentRepository.save(appointment);
	}

	public void cancelAppointment(AppointmentKey key) {
		appointmentRepository.deleteById(key);
	}
}
