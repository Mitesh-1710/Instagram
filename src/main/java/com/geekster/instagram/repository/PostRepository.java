package com.geekster.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.instagram.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
