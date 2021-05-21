package com.rest.libraryBack.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Livre;
import com.rest.libraryBack.repository.ClientRepository;
import com.rest.libraryBack.repository.LivreRepository;
import com.rest.libraryBack.service.LivreService;

@Service
public class LivreServiceImp implements LivreService {

	@Autowired
	private LivreRepository livreRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Livre> getAllLivres() {
		return livreRepository.findAll();
	}

	@Override
	public Livre getById(int id) {
		return livreRepository.findById(id).get();
	}

	/*
	 * Effectue une recherche d'un livre. le parametre "name" represente la chaine
	 * de caractere noté par l'utilisateur dans la barre de recherche
	 */
	@Override
	public List<Livre> getLivresByName(String name) {
		List<Livre> rechercheTitre = livreRepository.findByNomContaining(name);
		List<Livre> rechercheAuteur = livreRepository.findByAuteurContaining(name);
		List<Livre> rechercheGenre = livreRepository.findByGenreContaining(name);
		List<Livre> recherche = new ArrayList<Livre>();
		if (!rechercheTitre.isEmpty()) {
			return rechercheTitre;
		} else if (!rechercheAuteur.isEmpty()) {
			return rechercheAuteur;
		} else if (!rechercheGenre.isEmpty()) {
			return rechercheGenre;
		}
		return recherche;
	}

	@Override
	public void save(Livre livre) {
		livreRepository.save(livre);
	}

	@Override
	public List<Livre> getAllLivreReserveByMail(String mail) {
		Client client = clientRepository.findByMail(mail).get();
		List<Livre> allLivres = livreRepository.findAll();
		List<Livre> livre_reserve = new ArrayList<Livre>();
		for (Livre l : allLivres) {
			List<Client> liste_attente = l.getListe_attente();
			if (liste_attente != null) {
				for (Client c : liste_attente) {
					if (c.getId() == client.getId()) {
						livre_reserve.add(l);
					}
				}
			}
		}

		return livre_reserve;
	}

	/*
	 * Une fois le mail envoyer on utilise cette méthode pour retirer le premier
	 * utilisateur de la liste d'attente
	 */
	@Override
	public void removeFirstClientFromListeAttente(Livre livre) {
		List<Client> liste_attente = livre.getListe_attente();
		List<Client> liste_attente_new = new ArrayList<Client>();
		if (liste_attente != null) {
			int i = 0;
			for (Client c : liste_attente) {
				if (i != 0) {
					liste_attente_new.add(c);
				}
			}
			livre.setListe_attente(liste_attente_new);
			livreRepository.save(livre);
		}
	}

}
