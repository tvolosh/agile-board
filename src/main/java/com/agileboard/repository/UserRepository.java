package com.agileboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agileboard.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
