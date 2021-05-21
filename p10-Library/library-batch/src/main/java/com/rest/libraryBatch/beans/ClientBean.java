package com.rest.libraryBatch.beans;

import java.util.List;

public class ClientBean {

	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String  mail;
	
	private String password;
	
	private boolean admin;
	
	private List<ExemplaireBean> emprunt;
	
	public ClientBean() {
		this("","","");
	}
	
	public ClientBean(String nom, String prenom, String mail) {
		this.nom =nom;
		this.prenom=prenom;
		this.mail=mail;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<ExemplaireBean> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<ExemplaireBean> emprunt) {
		this.emprunt = emprunt;
	}
	
	
}

