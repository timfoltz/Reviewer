package com.tim.reviewer.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tim.reviewer.models.Event;
import com.tim.reviewer.models.User;
import com.tim.reviewer.services.EventService;
import com.tim.reviewer.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	
	@RequestMapping("/dashboard")
    public String dashboard(@ModelAttribute Event event, HttpSession session, Model model) {
    	Long id = (Long) session.getAttribute("userId");
    	
    	System.out.println(id);
    	
    	
    	if(id != null) {
    		List<Event> allEvents = eventService.allEvents();
    		User thisUser = userService.findUserById(id);
    		Long thisId = thisUser.getId();
    		System.out.println("this user "+thisId);
    		List<Event> notMyEvents = eventService.eventsNotUser(thisUser);
    		System.out.println(allEvents);
        	System.out.println(notMyEvents);
    		model.addAttribute("notMine", notMyEvents);
    		model.addAttribute("user", thisUser);
    		model.addAttribute("allEvents", allEvents);
    		
    		return "dashboard.jsp";
    	} else {
    		return "redirect:/";
    	}
    }
	
	@PostMapping("/events")
	public String newEvent(
								@Valid 
								@ModelAttribute Event event,
//								@PathVariable Long id,
								Model model, 
								BindingResult result,
								HttpSession session) {
		if(result.hasErrors()) {
			
			
			return "dashboard.jsp";
		} else {
			eventService.createEvent(event);
//			Long eventId = event.getId();
//			Event newEvent = eventService.findEvent(eventId);
//			Long userId = (Long) session.getAttribute("userId");
//			eventService.updateEvent(userId, newEvent);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/joinEvent/{id}")
	public String joinEvent(
							@PathVariable("id") Long id, 
							HttpSession session) {
		Event event = eventService.findEvent(id);
		Long userId = (Long) session.getAttribute("userId");
		eventService.updateEvent(userId, event);
		return "redirect:/dashboard";
		
	}
	@RequestMapping("/remove/{id}")
	public String leaveEvent(
			@PathVariable("id") Long id, 
			HttpSession session) {
		Event event = eventService.findEvent(id);
		Long userId = (Long) session.getAttribute("userId");
		eventService.leaveEvent(userId, event);
		return "redirect:/dashboard";
		
	}
	@RequestMapping("/deleteEvent/{id}")
	public String deleteEvent(
			@PathVariable("id") Long id, 
			HttpSession session) {
		Event event = eventService.findEvent(id);
		Long userId = (Long) session.getAttribute("userId");
		if(event.getCreator().getId().equals(userId)) {
			eventService.deleteEvent(event.getId());
			System.out.println("Deleted: "+event.getName());
			return "redirect:/dashboard";
			
		}
		System.out.println("cant delete:  "+event.getName());
		System.out.println("User ID: "+userId+"  Event creator ID: "+event.getCreator().getId());
		return "redirect:/dashboard";
		
	}
	@GetMapping("/events/{id}/edit")
	public String editEventForm(
			@PathVariable("id") Long eventId,
			HttpSession session,
			Model model) {
		Event thisEvent = eventService.findEvent(eventId);
		if(session.getAttribute("userId").equals(thisEvent.getCreator().getId()))  {
			User thisUser = userService.findUserById((Long) session.getAttribute("userId"));
			
			model.addAttribute("event", thisEvent);
			model.addAttribute("user", thisUser);
			return "editEvent.jsp";
		}return "redirect:/dashboard";
	}
	@PutMapping("/events/edit/{id}")
	public String editEvent(@Valid 
							@ModelAttribute("event") Event event,
							@PathVariable("id") Long eventId,
							BindingResult result,
							HttpSession session,
							Model model) {
		System.out.println("inside PUT");
		Event thisEvent = eventService.findEvent(eventId);
		if(result.hasErrors()) {
			User thisUser = userService.findUserById((Long) session.getAttribute("userId"));
			model.addAttribute("event", thisEvent);
			model.addAttribute("user", thisUser);
			return "editEvent.jsp";
		}else {
			eventService.editEvent(event);
			return "redirect:/events/"+event.getId();
		}
		
	}
	@GetMapping("/events/{id}")
	public String viewEvent() {
		
		return "showEvent.jsp";
	}
}
