package com.rest.libraryFront.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;
import com.rest.libraryFront.service.ClientService;
import com.rest.libraryFront.service.LivreService;

@Controller
public class LivreController {

	@Autowired
	private LivreService livreService;
	
	@Autowired
	private ClientService clientService;
		
	@GetMapping(value ="/livres")
	public String getLivre(Model model) {		
		List<LivreBean> livres = livreService.getLivres();
		model.addAttribute("livres", livres);
		return "livres";
	}
	
	@GetMapping(value ="/consultLivre")
	public String getConsultLivreLivre(Model model) {		
		List<LivreBean> livres = livreService.getLivres();
		model.addAttribute("livres", livres);
		return "livres";
	}
	
	@GetMapping(value="/search")
	public String getSearch(Model model) {
		model.addAttribute("livreBean", new LivreBean());
		return "search";
	}
	
	@PostMapping(value ="/search")
	public String postLivres(Model model, @ModelAttribute(value="livreBean") LivreBean livre) {
		String str = livre.getNom();
		List<LivreBean> livresRecherche = livreService.getLivresByNom(str);
		model.addAttribute("livresRecherche", livresRecherche);
		return "search";
	}
	
	/*
	 * Affiche la description d'un livre donné.
	 * Les données affichés sont : Titre, Date de parution, Genre, Auteur.
	 * Les dates de début et fin sont aussi affiché. 
	 * Au cas ou l'utilisateur souhaite emprunter le livre.
	 */
	@GetMapping(value="/description/{id}")
	public String getDescriptionLivre(@PathVariable("id") int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();	
		ClientBean client = clientService.getByMail(mail);		
		LivreBean livre = livreService.getById(id);
		ExemplaireBean exemplaire = livreService.getOneExemplaireDispo(livre);
		
		//Définition des dates
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date parution = livre.getParution();
		Calendar cal = Calendar.getInstance();
		Date debut = cal.getTime();
		cal.add(Calendar.MONTH, 1);
		Date fin = cal.getTime();
		
		//Définition des Date en String
		String strDateParu = formatter.format(parution);
		String strDateDebut=formatter.format(debut);
		String strDateFin=formatter.format(fin);
		
		//Envoi des models
		model.addAttribute("debut",strDateDebut);
		model.addAttribute("fin", strDateFin);
		model.addAttribute("parution",strDateParu);
		model.addAttribute("exemplaire", exemplaire);
		model.addAttribute("livre", livre);
		model.addAttribute("client", client);
		
		return "description";
	}
	
			
	@GetMapping(value="/addlivres")
	public String getAddLivres(Model model) {
		model.addAttribute("livreBean", new LivreBean());
		return "/addlivres";
	}
	
	@PostMapping(value="/addlivres")
	public String postAddLivres(Model model, @ModelAttribute("livreBean") LivreBean livre) {
		livreService.ajouterLivres(livre);
		return "/addlivres";
	}
	
	
}
