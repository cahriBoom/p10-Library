package com.rest.libraryFront.service;

import java.util.List;

import com.rest.libraryFront.beans.ExemplaireBean;

public interface ExemplaireService {

	ExemplaireBean prolongerExemplaire(int id);
	List<ExemplaireBean> getAllExemplaireByUser(String mail);
	void emprunt(int id, int idc);
	ExemplaireBean getById(int id);
}
