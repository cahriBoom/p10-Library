package com.rest.libraryBack.service;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Exemplaire;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExemplaireServiceIT {


    @Autowired
    private ClientService clientService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Test
    public void getAllByUserTest(){
        //Arrange
        Client client = clientService.getByMail("test@gmail.com").get();

        //Act
        List<Exemplaire> exemplaires = exemplaireService.getAllByUser(client);
        int size = exemplaires.size();

        //Assert
        Assert.assertEquals(1,size);

    }



    @Test
    public void getAllTest(){
        //Arrange-Act
        List<Exemplaire> all = exemplaireService.getAll();
        int size = all.size();
        int expected = 9;

        //Assert
        Assert.assertEquals(expected,size);
    }



    @Test
    public void getByIdTest(){
        //Arrange-Act
        Exemplaire exemplaire = exemplaireService.getById(1);
        int id_expected = 1;

        //Assert
        Assert.assertEquals(id_expected, exemplaire.getId());
    }



    @Test
    public void saveTest(){
        //Arrange
        Exemplaire exemplaire = new Exemplaire(1, null, "disponible");
        List<Exemplaire> all_before = exemplaireService.getAll();
        int size_before = all_before.size();

        //Act
        exemplaireService.save(exemplaire);
        List<Exemplaire> all_after = exemplaireService.getAll();
        int size_after = all_after.size();

        //Assert
        Assert.assertNotSame(size_before, size_after);
    }



    @Test
    public void rendreExemplaireTest(){
        //Arrange
        Exemplaire exemplaire = exemplaireService.getById(5);
        String etat_before = exemplaire.getEtat();

        //Act
        exemplaireService.rendreExemplaire(exemplaire);
        Exemplaire exemplaire_after = exemplaireService.getById(5);
        String etat_after = exemplaire_after.getEtat();

        //Assert
        Assert.assertNotSame(etat_before, etat_after);
    }

}
