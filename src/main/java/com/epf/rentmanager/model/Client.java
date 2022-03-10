package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

        private int id;
        private String nom;
        private String prenom;
        private String email;
        private LocalDate naissance ;



    public Client() {

    }

    public Client(int id, String nom, String prenom, String email, LocalDate naissance) {
        this.id=id;
        this.nom = nom.toUpperCase(Locale.ROOT);
        this.prenom = prenom.toUpperCase(Locale.ROOT);
        this.email = email;
        this.naissance=naissance;


    }
    public Client( String nom, String prenom, String email, LocalDate naissance) {
        this.nom = nom.toUpperCase(Locale.ROOT);
        this.prenom = prenom.toUpperCase(Locale.ROOT);
        this.email = email;
        this.naissance=naissance;


    }
    public Client( String nom, String prenom, String email) {
        this.nom = nom.toUpperCase(Locale.ROOT);
        this.prenom = prenom.toUpperCase(Locale.ROOT);
        this.email = email;

    }
    public Client(int id, String nom, String prenom, String email) {
        this.id=id;
        this.nom = nom.toUpperCase(Locale.ROOT);
        this.prenom = prenom.toUpperCase(Locale.ROOT);
        this.email = email;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase(Locale.ROOT);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom.toUpperCase(Locale.ROOT);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" +id +'\''+
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", naissance=" + naissance +
                '}';
    }





}
