package com.geekster.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.instagram.entity.PostEntity;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.service.AuthenticationService;
import com.geekster.instagram.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/post/{postId}")
	public ResponseEntity<Object> getPost(@PathVariable Long postId, @RequestParam String token,
			@RequestParam String email) {
		PostEntity post = null;
		HttpStatus status;
		if (authenticationService.authenticate(email, token)) {
			post = postService.getPost(postId);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.FORBIDDEN;
		}

		return new ResponseEntity<>(post, status);
	}

	@PostMapping("/post")
	public ResponseEntity<Object> savePost(@Valid @RequestBody Post post, @RequestParam String token,
			@RequestParam String email) {

		String message = "";
		HttpStatus status;
		if (authenticationService.authenticate(email, token)) {
			postService.savePost(post,email);
			message = "Post created successfully!";
			status = HttpStatus.OK;
		} else {
			message = "Invalid user password/email";
			status = HttpStatus.FORBIDDEN;
		}

		return new ResponseEntity<>(message, status);
	}

}
