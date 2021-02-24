package com.tim.reviewer.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    	List<Event> allEvents = eventService.allEvents();
    	if(id != null) {
    		User thisUser = userService.findUserById(id);
    		
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
			return "redirect:/dashboard";
		}
	}
}
