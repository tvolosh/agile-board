package com.agileboard.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tasks")
public class Task {

	@Id
	private String id;
	
	private String title;
	
	private String description;
	
	private Date createdOn = new Date();
	
	private TaskStatus status;
	
	@DBRef
	private User user;
	
	public Task(String id, String title, String description, Date createdOn, TaskStatus status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.createdOn = createdOn;
		this.status = status;
	}

	public Task() {
		super();
	}
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return Objects.equals(id, task.id) &&
				Objects.equals(createdOn, task.createdOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, createdOn);
	}

	@Override
	public String toString() {
		return "Task{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", createdOn=" + createdOn +
				", status=" + status +
				", user=" + user +
				'}';
	}
}
