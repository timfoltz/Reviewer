package com.tim.reviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull()
	private String name;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventDate;
	
	@NotNull
	private String location;
	
	@NotNull
	private String usState;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User creator;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="users_tasks",
			joinColumns = @JoinColumn(name="event_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> assignees;
	
	@OneToMany(mappedBy="comment", fetch=FetchType.LAZY)
	private List<Message> comments;
	

	public Task() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUsState() {
		return usState;
	}

	public void setUsState(String usState) {
		this.usState = usState;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	 protected void onCreate(){
   	this.createdAt = new Date();
   }
   @PreUpdate
   protected void onUpdate(){
       this.updatedAt = new Date();
   }

	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public List<User> getGoer() {
		return assignees;
	}
	
	public void setGoer(List<User> goer) {
		this.assignees = goer;
	}
	
	public List<User> getAssignees() {
		return assignees;
	}
	
	public void setAssignees(List<User> goers) {
		this.assignees = goers;
	}
	
	public List<Message> getComments() {
		return comments;
	}
	
	public void setComments(List<Message> comments) {
		this.comments = comments;
	}


}
