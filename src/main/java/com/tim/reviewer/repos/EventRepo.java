package com.tim.reviewer.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tim.reviewer.models.Task;
import com.tim.reviewer.models.User;

public interface EventRepo extends CrudRepository<Task, Long> {
	List<Task> findAll();
//	List<Task> findByUserIdNotContaining(User user);
	List<Task> findByCreatorIsNot(User user);
}
