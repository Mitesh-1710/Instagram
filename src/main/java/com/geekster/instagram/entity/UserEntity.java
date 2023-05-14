package com.geekster.instagram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "User")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="first_name")
	private String firstName;
	
	@NotNull
	@Column(name="last_name")
	private String lastName;
	
	@NotNull
	@Column(name="age")
	private Integer age;
	
	@NotNull
	@Email
	@Column(name="email")
	private String email;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="phone_number")
	private String phoneNumber;
}
