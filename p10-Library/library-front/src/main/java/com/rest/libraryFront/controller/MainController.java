package com.rest.libraryFront.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.service.ClientService;

@Controller
public class MainController {
		
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value ="/accueil")
	public String getAccueil(Model model) {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String current = auth.getName();
		if(current!="anonymousUser") {
			ClientBean client = clientService.getByMail(current);
			model.addAttribute("client", client);
		}
		model.addAttribute("current", current);
		return "accueil";
	}

}
