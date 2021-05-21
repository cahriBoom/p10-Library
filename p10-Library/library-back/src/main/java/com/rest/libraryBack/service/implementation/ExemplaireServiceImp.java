package com.rest.libraryBack.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Exemplaire;
import com.rest.libraryBack.repository.ClientRepository;
import com.rest.libraryBack.repository.ExemplaireRepository;
import com.rest.libraryBack.service.ExemplaireService;

@Service
public class ExemplaireServiceImp implements ExemplaireService{

	@Autowired
	private ExemplaireRepository exemplaireRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	/*
	 * Returns: Tous les exemplaires en cours d'emprunt par un utilisateur donné.
	 * 
	 */
	@Override
	public List<Exemplaire> getAllByUser(Client client) {
		Client c = clientRepository.findById(client.getId()).get();
		List<Exemplaire> exemplaires = c.getEmprunt();
		return exemplaires;
	}

	@Override
	public List<Exemplaire> getAll() {
		return exemplaireRepository.findAll();
	}
	
	
	@Override
	public Exemplaire getById(int id) {
		return exemplaireRepository.findById(id).get();
	}

	@Override
	public void save(Exemplaire exemplaire) {
		exemplaireRepository.save(exemplaire);		
	}

	
}
