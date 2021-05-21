package com.rest.libraryBatch.service.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rest.libraryBatch.beans.ClientBean;
import com.rest.libraryBatch.beans.ExemplaireBean;
import com.rest.libraryBatch.beans.LivreBean;
import com.rest.libraryBatch.proxies.BatchProxy;
import com.rest.libraryBatch.service.BatchService;

@Service
public class BatchServiceImp implements BatchService {

	@Autowired
	private BatchProxy batchProxy;

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public List<ClientBean> listRetardataire() {
		List<ClientBean> retardataire = batchProxy.getAllRetardataire();
		return retardataire;
	}

	@Override
	public List<ExemplaireBean> listExemlpaireRendu() {
		List<ExemplaireBean> exem_rendu = batchProxy.getAllExemplairesRendu();
		return exem_rendu;
	}

	/*
	 * Returns: La liste de tout les exemplaires en retard. A partir de la liste des
	 * utilisateur en retard.
	 */
	@Override
	public List<ExemplaireBean> listExemplaireRetard() {
		List<ClientBean> retardataire = this.listRetardataire();
		List<ExemplaireBean> exemplaires = new ArrayList<ExemplaireBean>();
		Date today = Calendar.getInstance().getTime();
		for (ClientBean c : retardataire) {
			List<ExemplaireBean> exemp = this.getExemplaireByUser(c);
			for (ExemplaireBean e : exemp) {
				if (today.after(e.getFin())) {
					exemplaires.add(e);
				}
			}
		}
		return exemplaires;
	}

	@Override
	public List<ExemplaireBean> getExemplaireByUser(ClientBean client) {
		List<ExemplaireBean> exemplaires = batchProxy.getAllExemplaireByUser(client.getId());
		return exemplaires;
	}

	/*
	 * Envoi un mail de rappel pour chaque exemplaires en retard a leurs emprunteur
	 */
	@Override
	public void sendMail() {
		List<ClientBean> retardataire = listRetardataire();
		List<ExemplaireBean> exemp = listExemplaireRetard();
		for (ClientBean c : retardataire) {
			SimpleMailMessage message = new SimpleMailMessage();
			for (ExemplaireBean e : exemp) {
				ClientBean emprunteur = batchProxy.getEmprunteurByExemplaire(e.getId());
				if (emprunteur.getId() == c.getId()) {
					message.setTo(c.getMail());
					message.setSubject("Délai dépassé");
					message.setText("Bonjour, Le Délai du livre : " + e.getLivre().getNom() + " emprunté est dépassé");
					emailSender.send(message);
				}
			}
		}
	}

	/*
	 * Envoi un mail à la première personne dans la liste d'attente Elle possède 48
	 * pour venir chercher le livre. Sinon sa réservation sera annulée
	 */
	@Override
	public void sendMailRetourLivres() {
		List<ExemplaireBean> rendu = listExemlpaireRendu();
		for (ExemplaireBean e : rendu) {
			LivreBean livre = e.getLivre();
			List<ClientBean> liste_attente = livre.getList_attente();
			if (liste_attente != null) {
				ClientBean first = liste_attente.get(0);
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(first.getMail());
				message.setSubject("Livre Disponible");
				message.setText("Bonjour, Le livre " + livre.getNom()
						+ " est désormais disponible, Vous avez 48h pour le récupérer. Sinon votre réservation sera annulée");

			}

		}

	}

}
