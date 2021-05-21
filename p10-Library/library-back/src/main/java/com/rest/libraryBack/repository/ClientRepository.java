package com.rest.libraryBack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rest.libraryBack.model.Client;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Integer>{

	Optional<Client> findByMail(String mail);
}
