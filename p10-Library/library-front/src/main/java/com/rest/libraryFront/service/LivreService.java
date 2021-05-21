package com.rest.libraryFront.service;

import java.util.Date;
import java.util.List;

import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;

public interface LivreService {

	LivreBean getById(int id);
	List<LivreBean> getLivres();
	List<LivreBean> getLivresByNom(String nom);
	ExemplaireBean getOneExemplaireDispo(LivreBean livre);
	void ajouterLivres(LivreBean livre);
	void reserverLivre(int id_livre, int id_client);
	List<LivreBean> getReservationByMail(String  mail);
	Date getDateRetourProche(LivreBean livre);
	boolean isOneExemplaireDisponible(LivreBean livre);
}
