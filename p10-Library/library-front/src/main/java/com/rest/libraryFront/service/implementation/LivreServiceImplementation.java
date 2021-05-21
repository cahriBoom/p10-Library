package com.rest.libraryFront.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;
import com.rest.libraryFront.proxies.LibraryProxy;
import com.rest.libraryFront.service.LivreService;

@Service("livreService")
public class LivreServiceImplementation implements LivreService {

	@Autowired
	private LibraryProxy libraryProxy;

	@Override
	public List<LivreBean> getLivres() {
		return libraryProxy.getLivres();
	}

	@Override
	public List<LivreBean> getLivresByNom(String nom) {
		return libraryProxy.getLivresByNom(nom);
	}

	@Override
	public void ajouterLivres(LivreBean livre) {
		libraryProxy.postAddlivres(livre);
	}

	@Override
	public LivreBean getById(int id) {
		LivreBean livre = libraryProxy.getById(id);
		List<ExemplaireBean> ex = new ArrayList<ExemplaireBean>();
		List<ExemplaireBean> exemplaires = libraryProxy.getAllExemplaire();
		for (ExemplaireBean e : exemplaires) {
			int idE = e.getLivre().getId();
			if (idE == id) {
				ex.add(e);
			}
		}
		livre.setListe_exemplaire(ex);
		return livre;
	}

	@Override
	public ExemplaireBean getOneExemplaireDispo(LivreBean livre) {
		List<ExemplaireBean> list = livre.getListe_exemplaire();
		for (ExemplaireBean e : list) {
			if (e.isDisponible()) {
				return e;
			}
		}
		return new ExemplaireBean();
	}

	@Override
	public void reserverLivre(int id_livre, int id_client) {
		libraryProxy.getReserverLivre(id_livre, id_client);

	}

	@Override
	public List<LivreBean> getReservationByMail(String mail) {
		List<LivreBean> livre_reserve = libraryProxy.getAllReservation(mail);
		return livre_reserve;
	}

	@Override
	public Date getDateRetourProche(LivreBean livre) {
		List<ExemplaireBean> exemplaires = livre.getListe_exemplaire();
		Date dateRetour = new Date();
		int i = 0;
		int size_liste = exemplaires.size();
		for (ExemplaireBean e : exemplaires) {
			Date ex_1 = e.getFin();
			Date ex_2 = new Date();
			if (i >= size_liste - 1) {
				ex_2 = exemplaires.get(size_liste - 1).getFin();
			} else {
				ex_2 = exemplaires.get(i + 1).getFin();
			}
			i++;

			// Compare les 2 date pour savoir laquelles est la moins récente
			if (ex_1.after(ex_2)) {
				dateRetour = ex_1;
			} else {
				dateRetour = ex_2;
			}

		}

		return dateRetour;
	}

	@Override
	public boolean isOneExemplaireDisponible(LivreBean livre) {
		List<ExemplaireBean> exemplaires = livre.getListe_exemplaire();
		for (ExemplaireBean e : exemplaires) {
			if (e.isDisponible()) {
				return true;
			}
		}

		return false;
	}

}
