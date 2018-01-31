package com.agileboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agileboard.model.Task;
import com.agileboard.model.TaskStatus;
import com.agileboard.model.User;
import com.agileboard.repository.TaskRepository;
import com.agileboard.repository.UserRepository;
import com.agileboard.service.dto.TaskDTO;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

		@Autowired
		private TaskRepository taskRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		@RequestMapping(method = RequestMethod.GET)
		public List<TaskDTO>getAllTasks(){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails){
				username = ((UserDetails)principal).getUsername();
			}
			else{
				username = principal.toString();
			}
			User user = userRepository.findByUsername(username);
			List<Task> tasks = taskRepository.findAllByUser(user);
			List<TaskDTO> listTaskDTO = new TaskDTO().convertList(tasks);
			return listTaskDTO;
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public TaskDTO createTask(@Valid @RequestBody Task task){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails){
				username = ((UserDetails)principal).getUsername();
			}
			else{
				username = principal.toString();
			}
			User user = userRepository.findByUsername(username);
			task.setUser(user);
			task.setStatus(TaskStatus.TODO);
			task = taskRepository.save(task);
			TaskDTO taskDTO = new TaskDTO(task);
			return taskDTO;
		}
		
		@RequestMapping(value="{id}", method = RequestMethod.GET)
		public ResponseEntity<TaskDTO> findOne(@PathVariable("id") String id){
			Task task = taskRepository.findOne(id);
			if(task == null){
				return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
			}
			TaskDTO taskDTO = new TaskDTO(task);
			return new ResponseEntity<TaskDTO>(taskDTO,HttpStatus.OK);
		}
		
		@RequestMapping(value="{id}", method = RequestMethod.PUT)
		public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody Task task, @PathVariable("id") String id){
			Task taskDb = taskRepository.findOne(id);
			if(taskDb == null){
				return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
			}
			else{
				taskDb.setTitle(task.getTitle());
				taskDb.setDescription(task.getDescription());
				taskDb.setStatus(task.getStatus());
				taskDb = taskRepository.save(taskDb);
				TaskDTO taskDTO = new TaskDTO(taskDb);
				return new ResponseEntity<TaskDTO>(taskDTO,HttpStatus.OK);
			}
		}
		
		@RequestMapping(value="{id}", method = RequestMethod.DELETE)
		public void deleteTask(@PathVariable("id") String id) {
			taskRepository.delete(id);
		}
}