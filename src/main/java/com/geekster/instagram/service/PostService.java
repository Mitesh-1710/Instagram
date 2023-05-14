package com.geekster.instagram.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.instagram.entity.PostEntity;
import com.geekster.instagram.entity.UserEntity;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.repository.PostRepository;
import com.geekster.instagram.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void savePost(@Valid Post post, String email) {
		
		UserEntity user = userRepository.findFirstByEmail(email);
		PostEntity postEntity = new PostEntity();
		postEntity = postEntity.toBuilder().postData(post.getPostData()).createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now()).user(user).build();
		postRepository.save(postEntity);

	}

	public PostEntity getPost(Long postId) {
		Optional<PostEntity> postEntity = postRepository.findById(postId);
		PostEntity post = null;
		if (postEntity.isPresent()) {
			post = postEntity.get();
		}
		return post;
	}

}
