package com.geekster.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.instagram.model.SignIn;
import com.geekster.instagram.model.User;
import com.geekster.instagram.response.ApplicationResponse;
import com.geekster.instagram.response.SignInResponse;
import com.geekster.instagram.service.AuthenticationService;
import com.geekster.instagram.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ApplicationResponse signup(@Valid @RequestBody User user) {
		return userService.signUp(user);
	}

	@PostMapping("/signin")
	public SignInResponse signin(@RequestBody SignIn signIn) {
		return userService.signIn(signIn);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User user, @PathVariable Long userId,
			@RequestParam String token) {
		String message = "";
		HttpStatus status;
		if (authenticationService.authenticate(user.getEmail(), token)) {
			userService.updateUser(user, userId);
			message = "User updated successfully!";
			status = HttpStatus.OK;
		} else {
			message = "Invalid user password/email";
			status = HttpStatus.FORBIDDEN;
		}

		return new ResponseEntity<>(message, status);
	}

}
