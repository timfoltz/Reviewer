package com.tim.reviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.reviewer.models.Task;
import com.tim.reviewer.models.User;
import com.tim.reviewer.repos.EventRepo;

@Service
public class EventService {
	
	@Autowired
	private EventRepo eventRepo;
	@Autowired
	private UserService userService;
//	******Create******
	public Task createEvent(Task event) {
		return eventRepo.save(event);
	}
//	******Read ONE******
	public Task findEvent(Long id) {
		Optional<Task> optionalEvent = eventRepo.findById(id);
		return optionalEvent !=null ? optionalEvent.get() : null;
	}
//	******Read ALL******
	public List<Task> allEvents()	{
		return eventRepo.findAll();
	}
//	******Read SOME******
	public List<Task> eventsNotUser(User user){
		return eventRepo.findByCreatorIsNot(user);
	}
//	******Update******
	public void updateEvent(Long userId, Task event) {
		List<User> goers = event.getAssignees();
		User going = userService.findUserById(userId);
		goers.add(going);
		eventRepo.save(event);
	}
	public void leaveEvent(Long userId, Task event) {
		List<User> goers = event.getAssignees();
		User going = userService.findUserById(userId);
		goers.remove(going);
		eventRepo.save(event);
	}
	public void editEvent(Task event) {
		eventRepo.save(event);
	}
//	******Destroy/Delete******
	public void deleteEvent(Long id) {
		 
		eventRepo.deleteById(id);
	}
}

