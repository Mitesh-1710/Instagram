package com.geekster.doctor.app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = AppointmentEntity.class, property = "id")
public class AppointmentEntity {

	@Id
	@EmbeddedId
	private AppointmentKey id;

	@ManyToOne
	@JoinColumn(name = "fk_doctor_doc_id")
	private DoctorEntity doctor;

	@OneToOne
	private PatientEntity patient;

}
