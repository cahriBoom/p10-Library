package com.rest.libraryFront.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;
import com.rest.libraryFront.proxies.LibraryProxy;
import com.rest.libraryFront.service.LivreService;

@Service("livreService")
public class LivreServiceImplementation implements LivreService{

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
		for(ExemplaireBean e:exemplaires) {
			int idE = e.getLivre().getId();
			if(idE==id) {
				ex.add(e);
			}
		}
		livre.setListe_exemplaire(ex);
		return livre;
	}

	@Override
	public ExemplaireBean getOneExemplaireDispo(LivreBean livre) {
		List<ExemplaireBean> list = livre.getListe_exemplaire();
		for(ExemplaireBean e:list) {
			if(e.isDisponible()) {
				return e;
			}
		}		
		return new ExemplaireBean();
	}

}
