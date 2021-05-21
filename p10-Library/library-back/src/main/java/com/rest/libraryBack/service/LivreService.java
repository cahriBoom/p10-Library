package com.rest.libraryBack.service;

import java.util.List;

import com.rest.libraryBack.model.Livre;

public interface LivreService {

	List<Livre> getAllLivres();

	List<Livre> getLivresByName(String name);

	Livre getById(int id);

	void save(Livre livre);

	List<Livre> getAllLivreReserveByMail(String mail);
	
	void removeFirstClientFromListeAttente(Livre livre);
}
