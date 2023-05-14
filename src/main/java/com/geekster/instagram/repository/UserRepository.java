package com.geekster.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.instagram.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

	  public UserEntity findFirstByEmail(String email);
	
}
