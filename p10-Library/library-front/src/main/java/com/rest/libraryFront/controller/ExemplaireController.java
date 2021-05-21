package com.rest.libraryFront.controller;

import java.text.SimpleDateFormat;
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


@Controller
public class ExemplaireController {
	
	@Autowired
	private ExemplaireService exemplaireService;
	
	/*
	 * Prolonge l'emprunt d'un exemplaire de 4 semaines.
	 * On ajoute 2 mois a la date de debut d'emprunt
	 */
	@GetMapping(value="/prolonge/{id}")
	public String getProlonge(Model model, @PathVariable("id") int id) {
		ExemplaireBean exemp = exemplaireService.prolongerExemplaire(id);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDateDebut = formatter.format(exemp.getDebut()); 
		String strDateFin = formatter.format(exemp.getFin());
		
		model.addAttribute("debut", strDateDebut);
		model.addAttribute("fin", strDateFin);
		
		return "prolonge";
	}
	
	@GetMapping(value="/information/{id}")
	public String getInfo(Model model, @PathVariable("id") int id) {
		ExemplaireBean ex = exemplaireService.getById(id);
		LivreBean livre = ex.getLivre();
		model.addAttribute("exemplaire", ex);
		model.addAttribute("livre", livre);
		return "/information";
	}
	
	/*
	 * Valide l'emprunt d'un livre.
	 * Et change la base de donnée avec la date du jour et la date de fin de l'emprunt
	 * Soit 4 semaines de prêt.
	 */
	@GetMapping("/emprunt/{id}&{idc}")
	public String getEmprunt(Model model, @PathVariable("id") int id, @PathVariable("idc") int idc) {
		Date debut = Calendar.getInstance().getTime();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date fin = cal.getTime();
		
		//Formatte les Dates pour une meilleurs lisibilité
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strdebut = formatter.format(debut);
		String strfin = formatter.format(fin);
		
		exemplaireService.emprunt(id, idc);
		model.addAttribute("debut",strdebut);
		model.addAttribute("fin",strfin);
		return "/emprunt";
	}
	
	
	@GetMapping(value="/consultEmprunt/{mail}")
	public String getConsultEmprunt(Model model, @PathVariable("mail") String mail) {
		List<ExemplaireBean> exemplaires = exemplaireService.getAllExemplaireByUser(mail);
		model.addAttribute("exemplaire", exemplaires);
		return "consultEmprunt";
	}
	
}
