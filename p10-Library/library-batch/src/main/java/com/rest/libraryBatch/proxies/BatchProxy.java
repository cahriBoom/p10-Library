package com.rest.libraryBatch.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.libraryBatch.beans.ClientBean;
import com.rest.libraryBatch.beans.ExemplaireBean;

@FeignClient(name="library-back", url="localhost:8080/libraryBack")
public interface BatchProxy {

	@GetMapping("/MailRetard")
	List<ClientBean> getAllRetardataire();
	
	@GetMapping(value="/exemplairesRetard/{id}")
	List<ExemplaireBean> getAllExemplaireByUser(@PathVariable("id") int id);
	
	@GetMapping(value="/emprunteurs/{id}")
	ClientBean getEmprunteurByExemplaire(@PathVariable("id") int id);
	
	
	@GetMapping(value="/returnBookVerification/{id}")
	void getReturnBookVerification(@PathVariable("id")int id) ;
	
	@GetMapping(value="/rendreLivre")
	List<ExemplaireBean> getAllExemplairesRendu();
}