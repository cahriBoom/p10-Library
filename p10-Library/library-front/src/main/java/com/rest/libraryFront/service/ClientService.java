package com.rest.libraryFront.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rest.libraryFront.beans.ClientBean;

public interface ClientService extends UserDetailsService{

	String addClient(ClientBean client);
	ClientBean getByMail(String mail);
	void cancelReservation(int id, String mail);
}
