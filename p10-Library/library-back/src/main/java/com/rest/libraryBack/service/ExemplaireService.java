package com.rest.libraryBack.service;

import java.util.List;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Exemplaire;

public interface ExemplaireService {

	Exemplaire getById(int id);

	List<Exemplaire> getAllByUser(Client client);

	List<Exemplaire> getAll();

	void save(Exemplaire exemplaire);

	void rendreExemplaire(Exemplaire exemplaire);
	
}
