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
import com.rest.libraryFront.service.ExemplaireService;
import com.rest.libraryFront.service.LivreService;

@Controller
public class LivreController {

	@Autowired
	private LivreService livreService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ExemplaireService exemplaireService;

	@GetMapping(value = "/livres")
	public String getLivre(Model model) {
		List<LivreBean> livres = livreService.getLivres();
		model.addAttribute("livres", livres);
		return "livres";
	}

	@GetMapping(value = "/consultLivre")
	public String getConsultLivreLivre(Model model) {
		List<LivreBean> livres = livreService.getLivres();
		model.addAttribute("livres", livres);
		return "livres";
	}

	@GetMapping(value = "/search")
	public String getSearch(Model model) {
		model.addAttribute("livreBean", new LivreBean());
		return "search";
	}

	@PostMapping(value = "/search")
	public String postLivres(Model model, @ModelAttribute(value = "livreBean") LivreBean livre) {
		String str = livre.getNom();
		List<LivreBean> livresRecherche = livreService.getLivresByNom(str);
		model.addAttribute("livresRecherche", livresRecherche);
		return "search";
	}

	/*
	 * Affiche la description d'un livre donné. Les données affichés sont : Titre,
	 * Date de parution, Genre, Auteur. Les dates de début et fin sont aussi
	 * affiché. Au cas ou l'utilisateur souhaite emprunter le livre.
	 */
	@GetMapping(value = "/description/{id}")
	public String getDescriptionLivre(@PathVariable("id") int id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		ClientBean client = clientService.getByMail(mail);
		LivreBean livre = livreService.getById(id);
		ExemplaireBean exemplaire = livreService.getOneExemplaireDispo(livre);

		// Permet de savoir le livre est en cours d'emprunt par l'utilisateur
		boolean alreadyEmprunter = false;
		List<ExemplaireBean> exemplaires_emprunt = exemplaireService.getAllExemplaireByUser(mail);
		for (ExemplaireBean e : exemplaires_emprunt) {
			if (e.getLivre().getId() == livre.getId()) {
				alreadyEmprunter = true;
			}
		}

		// Permet de savoir si l'utilisateur à déja fait une demande de réservation du
		// livre
		boolean alreadyReserver = false;
		List<LivreBean> allReservation = livreService.getReservationByMail(mail);
		for (LivreBean l : allReservation) {
			if (l.getId() == livre.getId()) {
				alreadyReserver = true;
			}
		}

		// Calcul du nombre d'exemplaire restant pour un livre donné en paramètre
		List<ExemplaireBean> exemplaires = livre.getListe_exemplaire();
		int nb_exemplaire_restant = 0;
		for (ExemplaireBean e : exemplaires) {
			if (e.isDisponible()) {
				nb_exemplaire_restant++;
			}
		}

		// Test si il y a au moins un exemplaire disponible à l'emprunt pour le livre.
		Date dateRetour = new Date();
		boolean isDispo = livreService.isOneExemplaireDisponible(livre);
		if (isDispo) {
			Calendar calendar = Calendar.getInstance();
			dateRetour = calendar.getTime();

		} else {
			// Calcul la date de retour la plus proche pour les livres indisponible
			dateRetour = livreService.getDateRetourProche(livre);
		}

		// Définition des dates
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date parution = livre.getParution();
		Calendar cal = Calendar.getInstance();
		Date debut = cal.getTime();
		cal.add(Calendar.MONTH, 1);
		Date fin = cal.getTime();

		// Définition des Date en String
		String strDateParu = formatter.format(parution);
		String strDateDebut = formatter.format(debut);
		String strDateFin = formatter.format(fin);
		String strDateRetour = formatter.format(dateRetour);

		// Envoi des models
		model.addAttribute("debut", strDateDebut);
		model.addAttribute("fin", strDateFin);
		model.addAttribute("parution", strDateParu);
		model.addAttribute("retour", strDateRetour);
		model.addAttribute("nb_exemplaire_restant", nb_exemplaire_restant);
		model.addAttribute("exemplaire", exemplaire);
		model.addAttribute("alreadyEmprunter", alreadyEmprunter);
		model.addAttribute("alreadyReserver", alreadyReserver);
		model.addAttribute("livre", livre);
		model.addAttribute("client", client);

		return "description";
	}

	@GetMapping(value = "/reserver/{id}&{id_client}")
	public String getReserver(Model model, @PathVariable("id") int id, @PathVariable("id_client") int client) {
		LivreBean livre = livreService.getById(id);

		int nb_exemplaire = livre.getNb_exemplaire();
		int max_reservation = nb_exemplaire * 2;
		int size_liste_attente = 0;
		if (livre.getListe_attente() != null) {
			size_liste_attente = livre.getListe_exemplaire().size();
		}

		String message = "";

		if (size_liste_attente < max_reservation) {
			livreService.reserverLivre(id, client);
			message = "Vous avez réservé le livre avec succès";
		} else {
			message = "Le nombre maximale de réservation faites pour ce livre est atteinte. Réessayé plus tard";
		}

		model.addAttribute("message", message);
		return "reserver";
	}

	@GetMapping(value = "/addlivres")
	public String getAddLivres(Model model) {
		model.addAttribute("livreBean", new LivreBean());
		return "/addlivres";
	}

	@PostMapping(value = "/addlivres")
	public String postAddLivres(Model model, @ModelAttribute("livreBean") LivreBean livre) {
		livreService.ajouterLivres(livre);
		return "/addlivres";
	}

}
