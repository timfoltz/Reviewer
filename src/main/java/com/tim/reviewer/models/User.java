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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Email
	@NotNull
	private String email;
	
	private String location;
	
	private List<String> roles;
	
	@Size(min=5, message="Password must be more than 4 characters")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	@OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<Task> createdEvents;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
				name="users_events",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "event_id")
			)
	private List<Task> goingToEvent;
	
	
	@OneToMany(mappedBy="comment", fetch=FetchType.LAZY)
	private List<Message> createdComments;
	
	
	public User() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public List<Task> getCreatedEvents() {
		return createdEvents;
	}


	public void setCreatedEvents(List<Task> createdEvents) {
		this.createdEvents = createdEvents;
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
	
	
	
	 public List<Message> getCreatedComments() {
		return createdComments;
	}


	public void setCreatedComments(List<Message> createdComments) {
		this.createdComments = createdComments;
	}


	public List<Task> getGoingToEvent() {
		return goingToEvent;
	}


	public void setGoingToEvent(List<Task> goingToEvent) {
		this.goingToEvent = goingToEvent;
	}


	@PrePersist
	 protected void onCreate(){
    	this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	

}

