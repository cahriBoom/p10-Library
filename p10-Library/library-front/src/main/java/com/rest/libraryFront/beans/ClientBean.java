package com.rest.libraryFront.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rest.libraryFront.config.BCryptManagerUtil;

public class ClientBean implements Serializable, UserDetails{

	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String  mail;
	
	private String password;
	
	private boolean admin;
	
	private List<ExemplaireBean> emprunt;
	
	public ClientBean() {
		this("","","","");
	}
	
	public ClientBean(String nom, String prenom, String mail, String password) {
		this.nom =nom;
		this.prenom=prenom;
		this.mail=mail;
		this.password = BCryptManagerUtil.passwordencoder().encode(password);
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

	public List<ExemplaireBean> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<ExemplaireBean> emprunt) {
		this.emprunt = emprunt;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "ClientBean [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password="
				+ password + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		String mail = this.getMail();
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}