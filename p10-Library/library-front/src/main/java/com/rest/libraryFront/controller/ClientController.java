package com.rest.libraryFront.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	


	@GetMapping(value ="/login")
	public ModelAndView getLogin(Model model) {		
		model.addAttribute("client", new ClientBean());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("/login");
	}
	
	@PostMapping(value="/login")
	public ModelAndView postLogin(Model model) {
		return new ModelAndView("redirect:/accueil");
	}
		
	
	@GetMapping(value="/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
	
	@GetMapping(value="/welcome/{mail}")
	public String getWelcome(Model model, @PathVariable("mail") String mail) {
		ClientBean client = clientService.getByMail(mail);
		model.addAttribute("client", client);
		return "welcome";
	}

	
	@GetMapping(value="/subscribe")
	public String getSubscribe(@ModelAttribute("client") ClientBean client) {
		return "subscribe";
	}
	
	
	@PostMapping(value="/subscribe")
	public String postSubscribe(Model model, @ModelAttribute("client") ClientBean client) {
		String msg = clientService.addClient(client);
		if(msg.equals("NOK")) {
			String message = "User Already Taken";
			model.addAttribute("msg", message);
			return "/subscribe";
		}else {
			return "/accueil";
			
		}
	}
	
	@GetMapping(value="/cancelReservation/{id}&{mail}")
	public String getCancelReservation(@PathVariable("id") int id, @PathVariable("mail") String mail) {
		clientService.cancelReservation(id, mail);
		return "/cancelReservation";
	}

}
