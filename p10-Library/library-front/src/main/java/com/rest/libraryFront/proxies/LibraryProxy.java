package com.rest.libraryFront.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.beans.ExemplaireBean;
import com.rest.libraryFront.beans.LivreBean;

@FeignClient(name = "library-back", url = "localhost:8080/libraryBack")
public interface LibraryProxy {

	//--------------------------------------------------------------------
	// Proxy Livres
	//--------------------------------------------------------------------
	@GetMapping(value = "/livres")
	List<LivreBean> getLivres();

	@GetMapping(value = "/description/{id}")
	LivreBean getById(@PathVariable("id") int id);

	@PostMapping(value = "/addlivres")
	void postAddlivres(@RequestBody LivreBean livre);

	@PostMapping(value = "/search/{recherche}")
	List<LivreBean> getLivresByNom(@PathVariable("recherche") String recherche);

	@GetMapping(value = "/consultEmprunt/{mail}")
	List<ExemplaireBean> getAllLivresEmprunt(@PathVariable("mail") String mail);

	@GetMapping(value = "/reserver/{id}&{id_client}")
	void getReserverLivre(@PathVariable("id") int id, @PathVariable("id_client") int id_client);
	
	@GetMapping(value = "/reservations/{mail}")
	List<LivreBean> getAllReservation(@PathVariable("mail") String mail);

	//--------------------------------------------------------------------
	// Proxy Exemplaire
	//--------------------------------------------------------------------
	@GetMapping(value="/returnBook")
	List<ExemplaireBean> getAllExemplaires();
	
	@GetMapping(value = "/emprunt/{id}&{idc}")
	void empruntLivre(@PathVariable("id") int id, @PathVariable("idc") int idc);

	@GetMapping(value = "/prolonge/{id}")
	ExemplaireBean prolongerExemplaire(@PathVariable("id") int id);

	@GetMapping(value = "/information/{id}")
	ExemplaireBean getExemplaireById(@PathVariable("id") int id);

	@GetMapping(value = "/exemplaires")
	List<ExemplaireBean> getAllExemplaire();
	
	@GetMapping(value="/returnBookVerification/{id}")
	LivreBean getReturnBookVerification(@PathVariable("id")int id);

	//--------------------------------------------------------------------
	// Proxy Client
	//--------------------------------------------------------------------
	@PostMapping(value = "/subscribe")
	String addClientSubscribe(@RequestBody ClientBean client);

	@GetMapping(value = "/welcome/{mail}")
	ClientBean getClientByMail(@PathVariable("mail") String mail);
	
	@GetMapping(value = "/cancelReservation{id}&{mail}")
	void getCancelReservation(@PathVariable("id")int id, @PathVariable("mail") String mail);

}
