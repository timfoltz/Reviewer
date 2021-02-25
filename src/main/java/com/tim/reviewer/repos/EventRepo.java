package com.tim.reviewer.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tim.reviewer.models.Event;
import com.tim.reviewer.models.User;

public interface EventRepo extends CrudRepository<Event, Long> {
	List<Event> findAll();
//	List<Event> findByUserIdNotContaining(User user);
	List<Event> findByCreatorIsNot(User user);
}
