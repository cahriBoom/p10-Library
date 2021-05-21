package com.rest.libraryFront.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ExemplaireBean {

	private int id;

	private LivreBean livre;

	private boolean disponible;

	private String etat;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date debut;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fin;

	private ClientBean emprunteur;

	private boolean prolonge;

	public ExemplaireBean() {
		this(0, new ClientBean(), "Disponible");
	}

	public ExemplaireBean(int id, ClientBean client, String etat) {
		this.id = id;
		this.emprunteur = client;
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public ClientBean getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(ClientBean emprunteur) {
		this.emprunteur = emprunteur;
	}

	public boolean isProlonge() {
		return prolonge;
	}

	public void setProlonge(boolean prolonge) {
		this.prolonge = prolonge;
	}

	public LivreBean getLivre() {
		return livre;
	}

	public void setLivre(LivreBean livre) {
		this.livre = livre;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
