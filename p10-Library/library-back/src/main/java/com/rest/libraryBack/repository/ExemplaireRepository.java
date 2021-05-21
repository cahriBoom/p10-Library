package com.rest.libraryBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rest.libraryBack.model.Exemplaire;

@RepositoryRestResource
public interface ExemplaireRepository extends JpaRepository<Exemplaire,Integer>{

}