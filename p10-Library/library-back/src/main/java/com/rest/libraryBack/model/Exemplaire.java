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

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Livre livre;
	
	private boolean disponible;
		
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date debut;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fin;

	@ManyToOne
	@JsonBackReference
	private Client emprunteur;
	
	private boolean prolonge;
	
	//Constructors
	public Exemplaire() {
		this(0,new Client());
	}


	public Exemplaire(int id, Client client) {
		this.id=id;
		this.emprunteur=client;
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

	
	
}
