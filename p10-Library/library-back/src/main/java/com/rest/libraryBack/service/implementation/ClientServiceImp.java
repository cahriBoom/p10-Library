package com.rest.libraryBack.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.repository.ClientRepository;
import com.rest.libraryBack.service.ClientService;

@Service
public class ClientServiceImp implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Optional<Client> getById(int id) {
		return clientRepository.findById(id);
	}

	@Override
	public Optional<Client> getByMail(String mail) {
		return clientRepository.findByMail(mail);
	}

	@Override
	public void addClient(Client client) {
		client.setEmprunt(null);
		clientRepository.save(client);

	}

	@Override
	public boolean isTaken(String mail) {
		List<Client> clients = clientRepository.findAll();
		for(Client c:clients) {
			if(c.getMail().equals(mail)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public void save(Client client) {
		clientRepository.save(client);
	}

}