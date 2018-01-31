package com.agileboard.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agileboard.model.Task;
import com.agileboard.model.User;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
	List<Task> findAllByUser(User user);
}