package com.rest.libraryBack.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Exemplaire;
import com.rest.libraryBack.model.Livre;
import com.rest.libraryBack.service.ClientService;
import com.rest.libraryBack.service.ExemplaireService;
import com.rest.libraryBack.service.LivreService;

@RestController
public class ExemplaireController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ExemplaireService exemplaireService;
	
	@Autowired
	private LivreService livreService;
	
	@GetMapping(value="/exemplaires")
	public List<Exemplaire>  getAllExemplaire(){
		return exemplaireService.getAll();
	}
	
	@GetMapping(value="/exemplairesRetard/{id}")
	public List<Exemplaire> getAllExemplaireByUser(@PathVariable("id") int id){
		Client client = clientService.getById(id).get();
		return client.getEmprunt();
	}
	
	/*
	 * Valide l'emprunt d'un livre.
	 * Et change la base de donnée avec la date du jour et la date de fin de l'emprunt
	 * Soit 4 semaines de prêt.
	 */
	@GetMapping("/emprunt/{id}&{idc}")
	public void empruntLivre(@PathVariable("id") int id, @PathVariable("idc")int idc) {
		Exemplaire exemplaire = exemplaireService.getById(id);
		Client client = clientService.getById(idc).get();
		Livre livre = exemplaire.getLivre();
		List<Exemplaire> ex = client.getEmprunt();
		int nb = livre.getNb_exemplaire()-1;
		livre.setNb_exemplaire(nb);
		
		//Create Date
		Date debut = Calendar.getInstance().getTime();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date fin = cal.getTime();
		exemplaire.setDisponible(false);
		exemplaire.setEmprunteur(client);
		exemplaire.setDebut(debut);
		exemplaire.setFin(fin);
		ex.add(exemplaire);
		client.setEmprunt(ex);
		
		//Save Data
		clientService.save(client);
		livreService.save(livre);
		exemplaireService.save(exemplaire);
	}
	
	/*
	 * Prolonge l'emprunt d'un exemplaire de 4 semaines.
	 * On ajoute 2 mois a la date de debut d'emprunt
	 * 
	 */
	@GetMapping(value="/prolonge/{id}")
	public Exemplaire prolongerExemplaire(@PathVariable("id") int id) {		
		Exemplaire exemplaire = exemplaireService.getById(id);
		//Prolonge la date d'un mois de l'exemplaire emprunte
		Calendar cal = Calendar.getInstance();
		cal.setTime(exemplaire.getDebut());
		cal.add(Calendar.MONTH, 2);
		Date dateFin = cal.getTime();
		exemplaire.setFin(dateFin);
		exemplaire.setProlonge(true);
		exemplaireService.save(exemplaire);
		return exemplaire;
		
	}
	
	
	
	@GetMapping(value="/information/{id}")
	public Exemplaire getExemplaireById(@PathVariable("id") int id) {
		return exemplaireService.getById(id);
	}
	
	@GetMapping("/consultEmprunt/{mail}")
	public List<Exemplaire> getAllLivresEmprunt(@PathVariable("mail") String mail){
		Client client = clientService.getByMail(mail).get();
		List<Exemplaire> exemplaires = exemplaireService.getAllByUser(client);
		return exemplaires;
	}
}
