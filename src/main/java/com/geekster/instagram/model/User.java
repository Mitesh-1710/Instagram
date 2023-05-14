package com.geekster.instagram.model;

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
public class User {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Integer age;

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;

	@NotNull
	private String phoneNumber;

}
