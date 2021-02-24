package com.tim.reviewer.repos;

import org.springframework.data.repository.CrudRepository;

import com.tim.reviewer.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
