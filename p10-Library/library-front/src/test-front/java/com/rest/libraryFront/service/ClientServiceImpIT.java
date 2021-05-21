package com.rest.libraryFront.service;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.beans.LivreBean;
import com.rest.libraryFront.proxies.LibraryProxy;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImpIT {

    @Autowired
    private LibraryProxy libraryProxy;

    @Test
    public void testAddClient(){
        //Arrange
        ClientBean client = new ClientBean("Nom","Prenom","mail@mail.com","password");

        //Act
        libraryProxy.addClientSubscribe(client);
        ClientBean client_test = libraryProxy.getClientByMail("mail@mail.com");

        //Assert
        Assert.assertEquals(client.getId(), client_test.getId());
    }


    @Test
    public void testCancelReservation(){
        //Arrange


        //Act



        //Assert



    }
}
