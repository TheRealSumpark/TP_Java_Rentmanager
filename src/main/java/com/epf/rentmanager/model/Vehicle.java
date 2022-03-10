package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {
        private Integer id;
        private String constructeur;
        private String modele;
        private int nb_places;


    public Vehicle() {
    }

    public Vehicle(String constructeur, String modele, int nb_places) {
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }
    public Vehicle(int id,String constructeur,String modele,  int nb_places) {
        this.id=id;
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public Integer getId() {
        return id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", modele='" + modele + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return nb_places == vehicle.nb_places && Objects.equals(constructeur, vehicle.constructeur) && Objects.equals(modele, vehicle.modele);
    }

}
