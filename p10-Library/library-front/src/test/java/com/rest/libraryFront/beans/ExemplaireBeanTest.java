package com.rest.libraryFront.beans;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ExemplaireBeanTest {
    @Test
    public void testConstructorWithParameters(){
        //Arrange
        int id =0;
        ClientBean client = new ClientBean();
        String etat = "disponible";

        //Act
        ExemplaireBean exemplaire = new ExemplaireBean(id, client, etat);

        //Assert
        Assert.assertEquals(id, exemplaire.getId());
        Assert.assertEquals(client, exemplaire.getEmprunteur());
        Assert.assertEquals(etat, exemplaire.getEtat());
    }

    @Test
    public void testConstructorWithoutParameters(){
        //Arrange
        ExemplaireBean exemplaire = new ExemplaireBean();

        //Act
        exemplaire.setId(0);
        exemplaire.setEtat("disponible");
        exemplaire.setEmprunteur(new ClientBean());

        //Assert
        Assert.assertEquals(0,exemplaire.getId());
        Assert.assertEquals(new ClientBean(), exemplaire.getEmprunteur());
        Assert.assertEquals("disponible", exemplaire.getEtat());
    }
}
