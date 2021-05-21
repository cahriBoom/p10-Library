package com.rest.libraryBack.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Exemplaire;
import com.rest.libraryBack.service.ClientService;
import com.rest.libraryBack.service.ExemplaireService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;	

	@Autowired
	private ExemplaireService exemplaireService;
	
	@PostMapping("/subscribe")
	public String addClientSubscribe(@RequestBody Client client){
		String mail = client.getMail();
		if(clientService.getByMail(mail).isEmpty()) {
			clientService.addClient(client);
			return "OK";
		}
		return "NOK";

	}
	
	@GetMapping(value="/emprunteurs/{id}")
	Client getEmprunteurByExemplaire(@PathVariable("id") int id) {
		Exemplaire exe = exemplaireService.getById(id);
		Client client = exe.getEmprunteur();
		return client;
	}
	
	/*
	 * Returns : Tous les client qui possède un ou plusieurs livre en retard
	 * Permet au Batch de retrouver les exemplaires en retard
	 */
	@GetMapping("/MailRetard")
	public List<Client>  getAllRetardataire(){
		List<Client> clients = clientService.getAll();
		List<Client> retardataire = new ArrayList<Client>();
		Date today = Calendar.getInstance().getTime();
		for(Client c: clients) {
			List<Exemplaire> exemp = c.getEmprunt();
			for(Exemplaire e:exemp) {
				if(today.after(e.getFin())) {
					retardataire.add(c);
					continue;
				}
			}
		}
		return retardataire;
	}
	
	@GetMapping("/welcome/{mail}")
	public Client getClientByMail(@PathVariable("mail") String mail) {
		Client client = clientService.getByMail(mail).get();
		return client;
		
	}
	
}
