package com.rest.libraryBack.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryBack.model.Livre;
import com.rest.libraryBack.repository.LivreRepository;
import com.rest.libraryBack.service.LivreService;

@Service
public class LivreServiceImp implements LivreService{

	@Autowired
	private LivreRepository livreRepository;
	
	@Override
	public List<Livre> getAllLivres() {
		return livreRepository.findAll();
	}

	@Override
	public Livre getById(int id) {
		return livreRepository.findById(id).get();
	}
	
	
	/*
	 * Effectue une recherche d'un livre.
	 * le parametre "name" represente la chaine de caractere noté par l'utilisateur
	 * dans la barre de recherche 
	 */
	@Override
	public List<Livre> getLivresByName(String name) {
		List<Livre> rechercheTitre = livreRepository.findByNomContaining(name);
		List<Livre> rechercheAuteur = livreRepository.findByAuteurContaining(name);
		List<Livre> rechercheGenre = livreRepository.findByGenreContaining(name);
		List<Livre> recherche = new ArrayList<Livre>();
		if(!rechercheTitre.isEmpty()) {
			return rechercheTitre;
		}else if(!rechercheAuteur.isEmpty()) {
			return rechercheAuteur;
		}else if(!rechercheGenre.isEmpty()) {
			return rechercheGenre;
		}
		return recherche;
	}

	@Override
	public void save(Livre livre) {
		livreRepository.save(livre);
	}

}
