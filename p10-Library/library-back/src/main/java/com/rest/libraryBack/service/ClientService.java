package com.rest.libraryBack.service;

import java.util.List;
import java.util.Optional;

import com.rest.libraryBack.model.Client;

public interface ClientService {

	Optional<Client> getById(int id);

	Optional<Client> getByMail(String mail);

	List<Client> getAll();

	boolean isTaken(String mail);

	void addClient(Client client);

	void save(Client client);

	void cancelReservation(int id, String mail);
	
}