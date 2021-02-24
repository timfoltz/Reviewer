package com.tim.reviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.reviewer.models.Event;
import com.tim.reviewer.repos.EventRepo;

@Service
public class EventService {
	
	@Autowired
	private EventRepo eventRepo;
//	******Create******
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}
//	******Read ONE******
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepo.findById(id);
		return optionalEvent !=null ? optionalEvent.get() : null;
	}
//	******Read ALL******
	public List<Event> allEvents()	{
		return eventRepo.findAll();
	}
//	******Read SOME******
	
//	******Update******
	public void updateEvent(Event event) {
		eventRepo.save(event);
	}
//	******Destroy/Delete******
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
}

