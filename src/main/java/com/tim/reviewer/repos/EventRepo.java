package com.tim.reviewer.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tim.reviewer.models.Event;

public interface EventRepo extends CrudRepository<Event, Long> {
	List<Event> findAll();
	
}
