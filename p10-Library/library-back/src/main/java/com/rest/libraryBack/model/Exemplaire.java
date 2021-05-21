package com.rest.libraryBack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Exemplaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Livre livre;

	private boolean disponible;

	private String etat;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date debut;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fin;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date debut_reservation;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fin_reservation;
	
	@ManyToOne
	@JsonBackReference
	private Client emprunteur;

	private boolean prolonge;

	// Constructors
	public Exemplaire() {
		this(0, new Client(), "Disponible");
	}

	public Exemplaire(int id, Client client, String etat) {
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

	public Date getDebut_reservation() {
		return debut_reservation;
	}

	public void setDebut_reservation(Date debut_reservation) {
		this.debut_reservation = debut_reservation;
	}

	public Date getFin_reservation() {
		return fin_reservation;
	}

	public void setFin_reservation(Date fin_reservation) {
		this.fin_reservation = fin_reservation;
	}

	public Client getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Client emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public boolean isProlonge() {
		return prolonge;
	}

	public void setProlonge(boolean prolonge) {
		this.prolonge = prolonge;
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
