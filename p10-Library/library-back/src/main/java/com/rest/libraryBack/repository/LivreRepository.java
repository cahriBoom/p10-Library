package com.rest.libraryBack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rest.libraryBack.model.Livre;

@RepositoryRestResource
public interface LivreRepository extends JpaRepository<Livre,Integer>{

	List<Livre> findByNomContaining(String nom);
	List<Livre> findByAuteurContaining(String auteur);
	List<Livre> findByGenreContaining(String genre);
}
