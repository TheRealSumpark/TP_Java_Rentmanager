package com.epf.rentmanager.utils.Validators;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientValidator {

    @Autowired
    private  ClientService clientService;

    public ClientValidator(ClientService clientService)
    {
        this.clientService=clientService;
    }


    public boolean isValid(Client client)
    {
        return this.isNameValid(client)&&
                this.isSurnameValid(client)&&
                this.isLegal(client) &&
                this.isMailValid(client);
    }




    /**
     * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */

    public  boolean isLegal(Client client)
    {
        return Period.between(client.getNaissance(), LocalDate.now()).getYears()>=18;
    }

    /**
     * Renvoie true si le mail de l'utilisateur passé en paramètre existe déja
     * @param client L'instance d’utilisateur à tester
     *
     */
    public boolean emailAlreadyTaken(Client client)
    {
        try {
            return this.clientService.findByEmail(client.getEmail())!=null;

        } catch (ServiceException e) {
            e.printStackTrace();

        }
            return false;
    }

    /**
     * Renvoie true si le nom de l'utilisateur passé en paramètre contient 3 caractères
     * @param client L'instance d’utilisateur à tester
     * @Return Résultat du test (>= 3 caractères)
     */
    public boolean isNameValid(Client client)
    {
        if(client.getNom().isBlank())
            return false;

        return client.getNom().length()>=3;
    }

    /**
     * Renvoie true si le prénom de l'utilisateur passé en paramètre contient 3 caractères
     * @param client L'instance d’utilisateur à tester
     * @Return Résultat du test (>= 3 caractères)
     */
    public boolean isSurnameValid(Client client)
    {
        if(client.getPrenom().isBlank())
            return false;
        return client.getPrenom().length()>=3;
    }


    public boolean isMailValid(Client client)
    {
        Pattern pattern= Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(client.getEmail());
        if (!matcher.find())
            return false;

        return true;
    }

}
