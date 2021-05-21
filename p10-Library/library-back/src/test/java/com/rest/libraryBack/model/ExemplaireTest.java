package com.rest.libraryBack.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ExemplaireTest {

    @Test
    public void testConstructorWithParameters(){
        //Arrange
        int id =0;
        Client client = new Client();
        String etat = "disponible";

        //Act
        Exemplaire exemplaire = new Exemplaire(id, client, etat);

        //Assert
        Assert.assertEquals(id, exemplaire.getId());
        Assert.assertEquals(client, exemplaire.getEmprunteur());
        Assert.assertEquals(etat, exemplaire.getEtat());
    }

    @Test
    public void testConstructorWithoutParameters(){
      //Arrange
      Exemplaire exemplaire = new Exemplaire();

      //Act
      exemplaire.setId(0);
      exemplaire.setEtat("disponible");
      exemplaire.setEmprunteur(new Client());

      //Assert
      Assert.assertEquals(0,exemplaire.getId());
      Assert.assertEquals(new Client(), exemplaire.getEmprunteur());
      Assert.assertEquals("disponible", exemplaire.getEtat());
    }
}
