package com.rest.libraryBack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.libraryBack.model.Exemplaire;
import com.rest.libraryBack.model.Livre;
import com.rest.libraryBack.service.LivreService;

@RestController
public class LivreController {

	@Autowired
	private LivreService livreService;
				
	@GetMapping("/livres")
	public List<Livre> getLivres() {
		List<Livre> livres = livreService.getAllLivres();		
		return livres;
	}
	
	@GetMapping("/description/{id}")
	public Livre getById(@PathVariable("id")int id) {
		Livre livre = livreService.getById(id);
		return livre;
	}
	
	/*
	 * Returns: une liste de livre correspondant à la recherche faites par l'utilisateur
	 * Les recherche sont : Titre, Auteur, Genre
	 * Les livres qui ne possèdent plus d'exemplaire disponible.
	 */
	@PostMapping("/search/{recherche}")
	public List<Livre> getLivresByNom(@PathVariable("recherche") String recherche){	
		List<Livre> allLivres = livreService.getLivresByName(recherche);
		List<Livre> livres = new ArrayList<Livre>();
		for(Livre l:allLivres) {			
			List<Exemplaire> allExemp = l.getListe_exemplaire();
			List<Exemplaire> exemplaire = new ArrayList<Exemplaire>();
			for(Exemplaire e:allExemp) {
				//Liste des exemplaire disponible pour chaque livre
				if(e.getEmprunteur()==null) {
					exemplaire.add(e);
				}
			}			
			int size = exemplaire.size();
			l.setNb_exemplaire(size);
			if(size>0) {
				livres.add(l);
			}
			livreService.save(l);
		}
		return livres;
	}
	
	/*
	 * Permet à un ADMIN d'enregistrer dans la base de données un nouveau livre
	 * Si le livre existe deja. Un exemplaire sera ajouté
	 */
	@PostMapping(value="/addlivres")
	public void postAddlivres(@RequestBody Livre livre) {
		List<Livre> all = livreService.getAllLivres();
		for(Livre l:all) {
			int size = l.getNb_exemplaire();
			if(l.equals(livre)) {
				l.setNb_exemplaire(size+1);
				livreService.save(l);
			}else {
				livreService.save(livre);
			}
		}		
	}
}
