package com.agileboard.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agileboard.model.Task;
import com.agileboard.model.TaskStatus;

public class TaskDTO {

	private String id;
	private String title;
	private String description;
	private Date createdOn;
	private TaskStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskDTO(){
		super();
	}

	public TaskDTO(String id, String title, String description, Date createdOn, 
			TaskStatus status, Boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.createdOn = createdOn;
		this.description = description;
		this.status = status;
	}
	
	public TaskDTO(Task task){
		super();
		this.id = task.getId();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.status = task.getStatus();
		this.createdOn = task.getCreatedOn();
	}
	
	public List<TaskDTO> convertList(List<Task> task){
		List<TaskDTO> listTaskDTO = new ArrayList<TaskDTO>();
		if(!task.isEmpty()){
			for(Task taskIterator: task){
				TaskDTO taskDTO = new TaskDTO(taskIterator);
				listTaskDTO.add(taskDTO);
			}
		}
		return listTaskDTO;
	}
}