package com.rest.libraryFront.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class LivreBean {

	private int id;
	
	private String nom;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date parution;
		
	private int nb_exemplaire;
	
	private String auteur;
		
	private String genre;
	
	private String synopsis;
	
	List<ExemplaireBean> liste_exemplaire;
				
	public LivreBean() {
		this("",Calendar.getInstance().getTime(),"","");
	}
	
	public LivreBean(String nom, Date d, String auteur, String genre) {
		this.nom=nom;
		this.parution = d;
		this.auteur=auteur;
		this.genre=genre;
	}

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
	
	public int getNb_exemplaire() {
		return nb_exemplaire;
	}

	public void setNb_exemplaire(int nb_exemplaire) {
		this.nb_exemplaire = nb_exemplaire;
	}

	public List<ExemplaireBean> getListe_exemplaire() {
		return liste_exemplaire;
	}

	public void setListe_exemplaire(List<ExemplaireBean> liste_exemplaire) {
		this.liste_exemplaire = liste_exemplaire;
	}

	@Override
	public String toString() {
		return "LivreBean [id=" + id + ", nom=" + nom + ", parution=" + parution + ", nb_dispo=" + "]";
	}

	
}