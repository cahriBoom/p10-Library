package com.rest.libraryFront.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.proxies.LibraryProxy;
import com.rest.libraryFront.service.ExemplaireService;

@Service("ExemplaireService")
public class ExemplaireServiceImplementation implements ExemplaireService{

	@Autowired
	private LibraryProxy libraryProxy;
	
	@Override
	public ExemplaireBean prolongerExemplaire(int id) {
		return libraryProxy.prolongerExemplaire(id);
	}

	@Override
	public List<ExemplaireBean> getAllExemplaireByUser(String mail) {
		return libraryProxy.getAllLivresEmprunt(mail);
	}
	
	@Override
	public void emprunt(int id, int idc) {
		libraryProxy.empruntLivre(id, idc);
		
	}

	@Override
	public ExemplaireBean getById(int id) {
		return libraryProxy.getExemplaireById(id);
	}

}
