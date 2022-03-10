package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Menu {


        public static void start()
        {

            Scanner sc = new Scanner(System.in);
            int choice;

            System.out.println("1 : Creer client\n");
            System.out.println("2 : Lister tous les clients\n");
            System.out.println("3 : Creer un vehicule\n");
            System.out.println("4 : Lister tous les vehicules\n");
            System.out.println("5 : Supprimer un client\n");
            System.out.println("6 : Supprimer un vehicule\n");
            System.out.println("0 : Quitter\n");

            choice = sc.nextInt();

            while (choice!=0)
            {
                System.out.println("1 : Creer client\n");
                System.out.println("2 : Lister tous les clients\n");
                System.out.println("3 : Creer un vehicule\n");
                System.out.println("4 : Lister tous les vehicules\n");
                System.out.println("5 : Supprimer un client\n");
                System.out.println("6 : Supprimer un vehicule\n");
                System.out.println("0 : Quitter\n");

                switch (choice)
                {
                    case 1:
                       /* try {
                           // ClientService.getInstance().create(Menu.saisirInfoClient());
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }*/
                        ;break;
                    case 2: ;break;
                    case 3: ;break;
                    case 4: ;break;
                    case 5: ;break;
                    case 6: ;break;
                    default: break;
                }
            }




        }


        public static Client saisirInfoClient()
        {
            Scanner sc = new Scanner(System.in);
            String nom;
            String prenom;
            String email;
            int j,m,an;
            do {
                System.out.println("Nom :\n");
                nom=sc.nextLine();
            }while (nom. isBlank());

            do {
                System.out.println("Prenom :\n");
                prenom=sc.nextLine();
            }while (prenom. isBlank());

            do {
                System.out.println("Email :\n");
                email=sc.nextLine();
            }while (emailVerifier(email));

            System.out.println("Date de naissance  :\n");
            System.out.println("Jour");
            j=sc.nextInt();
            System.out.println("Mois");
            m=sc.nextInt();
            System.out.println("Année");
            an=sc.nextInt();
            Client c = new Client(nom,prenom,email, LocalDate.of(an,m,j));
            return c;
        }

        public static boolean emailVerifier(String email)
        {
            Pattern pattern= Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            Matcher matcher = pattern.matcher(email);
            if (matcher.find())
                return true;
            return false;
        }


}
