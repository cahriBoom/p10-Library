package com.rest.libraryBack.service;

import com.rest.libraryBack.model.Client;
import com.rest.libraryBack.model.Livre;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivreServiceIT {

    @Autowired
    private LivreService livreService;

    @Test
    public void getAllLivresTest(){
        //Arrange-Act
        List<Livre> all = livreService.getAllLivres();
        int size = all.size();
        int expected_number = 5;

        //Assert
        Assert.assertEquals(expected_number,size);
    }

    @Test
    public void getByIdTest(){
        //Arrange-Act
        Livre livre = livreService.getById(2);
        String expected_name = "Harry Potter";

        //Assert
        Assert.assertEquals(expected_name,livre.getNom());
    }


    @Test
    public void getLivresByNameTest(){
        //Arrange
        String name = "h";

        //Act
        List<Livre> recherche = livreService.getLivresByName(name);
        int size = recherche.size();
        int expected_size = 2;

        //Assert
        Assert.assertEquals(expected_size,size);
    }


    @Test
    public void saveTest(){
        //Arrange
        Livre livre = new Livre("Testologie",new Date(),"Jean Test","Documentaire");
        List<Livre> all_before = livreService.getAllLivres();
        int size_before = all_before.size();

        //Act
        livreService.save(livre);
        List<Livre> all_after = livreService.getAllLivres();
        int size_after = all_after.size();

        //Assert
        Assert.assertNotSame(size_before,size_after);
    }



    @Test
    public void getLivreReserveByMailTest(){
        //Arrange
        String mail = "test@gmail.com";
        int expected_number = 1;

        //Act
        List<Livre> liste_reserve = livreService.getAllLivreReserveByMail(mail);
        int size = liste_reserve.size();

        //Assert
        Assert.assertEquals(expected_number,size);
    }


    @Test
    public void removeFirstClientFromListeAttenteTest(){
        //Arrange
        Livre livre = livreService.getById(2);
        List<Client> liste_attente = livre.getListe_attente();
        int size_before = 0;
        if (liste_attente!=null){
            size_before = liste_attente.size();
        }

        //Act
        livreService.removeFirstClientFromListeAttente(livre);
        Livre livre_after = livreService.getById(2);
        List<Client> liste_attente_after = livre_after.getListe_attente();
        int size_after = 0;
        if(liste_attente_after!=null){
            size_after = liste_attente_after.size();
        }

        //Assert
        Assert.assertNotSame(size_before,size_after);

    }
}
