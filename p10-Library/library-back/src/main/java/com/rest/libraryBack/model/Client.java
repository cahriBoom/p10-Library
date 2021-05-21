package com.rest.libraryBack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String  mail;
	
	private String password;

	private boolean admin;
	
	@JsonManagedReference
	@OneToMany(mappedBy="emprunteur")
	private List<Exemplaire> emprunt = new ArrayList<Exemplaire>();
	
	//Constructors
	public Client() {
		this("","","","");
	}
	
	public Client(String nom, String prenom, String mail,String password) {
		this.nom =nom;
		this.prenom=prenom;
		this.mail=mail;
		this.password=password;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Exemplaire> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<Exemplaire> emprunt) {
		this.emprunt = emprunt;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}