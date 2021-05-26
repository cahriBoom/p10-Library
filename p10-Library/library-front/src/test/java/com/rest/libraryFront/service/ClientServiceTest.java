package com.rest.libraryFront.service;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.config.BCryptManagerUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ClientServiceTest {


    @Test
    public void testEncryptPassword(){
        //Arrange
        String password = "password";

        //Act
        ClientBean client = new ClientBean("nom","prenom","mail", BCryptManagerUtil.passwordencoder().encode(password));

        //Assert
        Assert.assertNotSame(password,client.getPassword());

    }
}
