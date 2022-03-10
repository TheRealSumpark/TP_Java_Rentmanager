package com.epf.rentmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Reservation {


    private int id;
    private Client client;
    private Vehicle vehicle;
    private int client_id;
    private int vehicle_id;
    private LocalDateTime debut;
    private LocalDateTime fin;



    public Reservation() {
    }

    public Reservation(Client client, Vehicle vehicle, LocalDateTime debut, LocalDateTime fin) {
        this.client = client;
        this.vehicle = vehicle;
        this.debut =debut;
        this.fin=fin;

    }

    public Reservation(int id,Client client, Vehicle vehicle, LocalDateTime debut, LocalDateTime fin) {
        this.id=id;
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin=fin;
    }


    public Reservation(int id,int client_id, int vehicle_id, LocalDateTime debut, LocalDateTime fin) {
        this.id=id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut =debut;
        this.fin=fin;;
    }

    public Reservation(int client_id, int vehicle_id, LocalDateTime debut, LocalDateTime fin) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;

        this.debut =debut;
        this.fin=fin;
    }




    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicule(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }


}
