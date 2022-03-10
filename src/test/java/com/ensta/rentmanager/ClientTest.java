package com.ensta.rentmanager;


import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.Validators.ClientValidator;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ClientTest {


    @Mock
    ClientService clientService;
    @Mock
    ClientDao clientDao;

    @InjectMocks
    ClientValidator clientsValidator;


    @Test
    public void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client legalClient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(1998,1,1));

        // Then
        assertTrue(clientsValidator.isLegal(legalClient));
    }

    @Test
    public void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client illegaClient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2008,1,1));

        // Then
        assertFalse(clientsValidator.isLegal(illegaClient));
    }


    @Test
    public void emailAlreadyTaken_should_return_true_when_email_is_taken() throws DaoException, ServiceException {
        //Given
        Client clientWithExistingEmail = new Client("John","Doe","jean.dupont@email.com",LocalDate.of(1998,1,1));

        //Then
        when(this.clientService.findByEmail(clientWithExistingEmail.getEmail())).thenReturn(new Client("John","Doe","jean.dupont@email.com",LocalDate.of(1998,1,1)));

        assertTrue(clientsValidator.emailAlreadyTaken(clientWithExistingEmail));

    }

    @Test
    public void emailAlreadyTaken_should_return_false_when_email_is_not_taken()
    {
        //Given
        Client clientWithNonExistingEmail = new Client("John","Doe","john.doe@ensta.fr",LocalDate.of(1998,1,1));

        //Then
        assertFalse(clientsValidator.emailAlreadyTaken(clientWithNonExistingEmail));
    }

    @Test
    public void isNameValid_should_return_true_if_name_contains_three_characters()
    {
        //Given
        Client clientwithValidName = new Client("John","Doe","john.doe@ensta.fr",LocalDate.of(1998,1,1));

        //Then
        assertTrue(clientsValidator.isNameValid(clientwithValidName));
    }
    @Test
    public void isNameValid_should_return_false_if_name_contains_less_than_three_characters()
    {
        //Given
        Client clientWithInvalidName = new Client("J","Doe","john.doe@ensta.fr",LocalDate.of(1998,1,1));

        //Then
        assertFalse(clientsValidator.isNameValid(clientWithInvalidName));
    }

    @Test
    public void isSurnameValid_should_return_true_if_surname_contains_three_characters()
    {
        //Given
        Client clientwithValidSurname = new Client("John","Doe","john.doe@ensta.fr",LocalDate.of(1998,1,1));

        //Then
        assertTrue(clientsValidator.isSurnameValid(clientwithValidSurname));
    }
    @Test
    public void isSurnameValid_should_return_false_if_surname_contains_less_than_three_characters()
    {
        //Given
        Client clientWithInvalidSurnameName = new Client("John","D","john.doe@ensta.fr",LocalDate.of(1998,1,1));

        //Then
        assertFalse(clientsValidator.isSurnameValid(clientWithInvalidSurnameName));
    }






}
