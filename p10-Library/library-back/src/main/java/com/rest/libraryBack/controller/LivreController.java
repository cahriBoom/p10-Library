package com.rest.libraryBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Livre;
import com.rest.libraryBack.service.ClientService;
import com.rest.libraryBack.service.LivreService;

@RestController
public class LivreController {

	@Autowired
	private LivreService livreService;

	@Autowired
	private ClientService clientService;

	@GetMapping(value = "/livres")
	public List<Livre> getLivres() {
		List<Livre> livres = livreService.getAllLivres();
		return livres;
	}

	@GetMapping(value = "/description/{id}")
	public Livre getById(@PathVariable("id") int id) {
		Livre livre = livreService.getById(id);
		return livre;
	}

	/*
	 * Returns: une liste de livre correspondant à la recherche faites par
	 * l'utilisateur Les recherche sont : Titre, Auteur, Genre Les livres qui ne
	 * possèdent plus d'exemplaire disponible.
	 */
	@PostMapping("/search/{recherche}")
	public List<Livre> getLivresByNom(@PathVariable("recherche") String recherche) {
		List<Livre> allLivres = livreService.getLivresByName(recherche);
		return allLivres;

	}

	@GetMapping(value = "/reserver/{id}&{id_client}")
	public void getReserverLivre(@PathVariable("id") int id, @PathVariable("id_client") int id_client) {
		Client client = clientService.getById(id_client).get();
		Livre livre = livreService.getById(id);

		// Ajoute le client à la liste d'attente du livre
		int nb_exemplaire = livre.getNb_exemplaire();
		int nb_max_reservation = nb_exemplaire * 2;
		int size_liste_attente = livre.getListe_attente().size();

		// Vérifie si la liste des reservation n'est pas pleine
		if (size_liste_attente < nb_max_reservation) {
			livre.addClientToListe_attente(client);
			livreService.save(livre);
		}

	}

	/*
	 * Retourne la liste des livre réservés par l'utilisateur dont le mail est donné
	 * en paramètre
	 */
	@GetMapping(value="/reservations/{mail}")
	public List<Livre> getAllReservation(@PathVariable("mail") String mail) {
		return livreService.getAllLivreReserveByMail(mail);
	}

	/*
	 * Permet à un ADMIN d'enregistrer dans la base de données un nouveau livre Si
	 * le livre existe deja. Un exemplaire sera ajouté
	 */
	@PostMapping(value = "/addlivres")
	public void postAddlivres(@RequestBody Livre livre) {
		List<Livre> all = livreService.getAllLivres();
		for (Livre l : all) {
			int size = l.getNb_exemplaire();
			if (l.equals(livre)) {
				l.setNb_exemplaire(size + 1);
				livreService.save(l);
			} else {
				livreService.save(livre);
			}
		}
	}

}
