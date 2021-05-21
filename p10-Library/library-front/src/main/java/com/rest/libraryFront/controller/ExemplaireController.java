package com.rest.libraryFront.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;
import com.rest.libraryFront.service.ExemplaireService;
import com.rest.libraryFront.service.LivreService;

@Controller
public class ExemplaireController {

	@Autowired
	private ExemplaireService exemplaireService;
		
	@Autowired
	private LivreService livreService;

	/*
	 * Prolonge l'emprunt d'un exemplaire de 4 semaines. On ajoute 2 mois a la date
	 * de debut d'emprunt
	 */
	@GetMapping(value = "/prolonge/{id}")
	public String getProlonge(Model model, @PathVariable("id") int id) {
		ExemplaireBean exemp = exemplaireService.getById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDateDebut = formatter.format(exemp.getDebut());
		String strDateFin = formatter.format(exemp.getFin());

		Date today = Calendar.getInstance().getTime();
		boolean peutEtreProlonger = true;
		String message = "Prolongation Validée";
		
		// Correction Ticket_2: si la date butoire est dépassée, le prêt ne peux plus
		// être prolonger
		if(exemp.getFin().before(today)) {
			peutEtreProlonger = false;
			message = "Date butoire dépassée, le prêt ne peux plus être prolongé";
		}
		
		if(peutEtreProlonger) {
			exemp = exemplaireService.prolongerExemplaire(id);
			strDateDebut = formatter.format(exemp.getDebut());
			strDateFin = formatter.format(exemp.getFin());
		}
		
		model.addAttribute("message",message);
		model.addAttribute("peutEtreProlonger", peutEtreProlonger);
		model.addAttribute("debut", strDateDebut);
		model.addAttribute("fin", strDateFin);

		return "prolonge";
	}

	@GetMapping(value = "/information/{id}")
	public String getInfo(Model model, @PathVariable("id") int id) {
		ExemplaireBean ex = exemplaireService.getById(id);
		LivreBean livre = ex.getLivre();
		Date today = Calendar.getInstance().getTime();
		boolean peutEtreProlonger = true;

		// Correction Ticket_2: si la date butoire est dépassée, le prêt ne peux plus
		// être prolonger
		if (ex.getFin().before(today)) {
			peutEtreProlonger = false;
		}

		model.addAttribute("peutEtreProlonger", peutEtreProlonger);
		model.addAttribute("exemplaire", ex);
		model.addAttribute("livre", livre);
		return "/information";
	}

	/*
	 * Valide l'emprunt d'un livre. Et change la base de donnée avec la date du jour
	 * et la date de fin de l'emprunt Soit 4 semaines de prêt.
	 */
	@GetMapping("/emprunt/{id}&{idc}")
	public String getEmprunt(Model model, @PathVariable("id") int id, @PathVariable("idc") int idc) {
		Date debut = Calendar.getInstance().getTime();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date fin = cal.getTime();

		// Formatte les Dates pour une meilleurs lisibilité
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strdebut = formatter.format(debut);
		String strfin = formatter.format(fin);

		exemplaireService.emprunt(id, idc);
		model.addAttribute("debut", strdebut);
		model.addAttribute("fin", strfin);
		return "/emprunt";
	}
	/*
	 * Retourne la liste de tout les emprunts, dans le but de notifier un livre rendu
	 * Visible uniquement par l'admin
	 */
	@GetMapping(value="/returnBook")
	public String getReturnBook(Model model) {	
		List<ExemplaireBean> allLivres = exemplaireService.getAll();
		List<ExemplaireBean> allEmprunt = new ArrayList<ExemplaireBean>();
		for(ExemplaireBean e:allLivres) {
			if(!e.isDisponible()) {
				allEmprunt.add(e);
			}
		}

		model.addAttribute("allEmprunt", allEmprunt);
		return "/returnBook";
	}
	
	/*
	 * Permet de notifier qu'un livre à été rendu
	 */
	@GetMapping(value="/returnBookVerification/{id}")
	public String getReturnBookVerification(Model model, @PathVariable("id")int id) {	
		exemplaireService.returnBook(id);
		return "/returnBookVerification";
	}

	/*
	 * Permet à l'utilisateur de consulter sa liste d'ouvrages emprunter et réserver
	 */
	@GetMapping(value = "/consultEmprunt/{mail}")
	public String getConsultEmprunt(Model model, @PathVariable("mail") String mail) {
		List<ExemplaireBean> exemplaires = exemplaireService.getAllExemplaireByUser(mail);
		List<LivreBean> livre_reserve = livreService.getReservationByMail(mail);				
		model.addAttribute("livre_reserve", livre_reserve);
		model.addAttribute("exemplaire", exemplaires);
		return "consultEmprunt";
	}

}
