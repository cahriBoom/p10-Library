package com.rest.libraryBack.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livre {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date parution;
	
	private int nb_exemplaire;
	
	private String auteur;
	
	private String genre;
	
	private String synopsis;
	
	@JsonIgnore
	@OneToMany(mappedBy="livre")
	private List<Exemplaire> liste_exemplaire;
		
	//Constructors
	public Livre() {
		this("",Calendar.getInstance().getTime(),"","");
	}
	
	public Livre(String nom, Date d,String auteur, String genre) {
		this.nom=nom;
		this.parution = d;
		this.auteur=auteur;
		this.genre=genre;
	}

	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getParution() {
		return parution;
	}

	public void setParution(Date parution) {
		this.parution = parution;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Exemplaire> getListe_exemplaire() {
		return liste_exemplaire;
	}

	public void setListe_exemplaire(List<Exemplaire> liste_exemplaire) {
		this.liste_exemplaire = liste_exemplaire;
	}

	public int getNb_exemplaire() {
		return nb_exemplaire;
	}

	public void setNb_exemplaire(int nb_exemplaire) {
		this.nb_exemplaire = nb_exemplaire;
	}	
	
	
}