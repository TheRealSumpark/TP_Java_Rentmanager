package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.ui.servlets.reservationServlets.ReservationCreateServlet;
import com.epf.rentmanager.utils.Validators.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")

public class ReservationService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private ReservationValidator reservationValidator;

    public static ReservationService instance;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }



   public List<Reservation> findAll() throws  ServiceException
   {
       try {
           List<Reservation> reservationList=reservationDao.findAll();
           for (Reservation resevation :reservationList)
           {
               resevation.setClient(clientService.findById(resevation.getClient_id()));
               resevation.setVehicule(vehicleService.findById(resevation.getVehicle_id()));
           }
         return reservationList;
       } catch (DaoException e) {
           e.printStackTrace();
            throw new ServiceException();
       }
   }

   public List<Reservation> findResaByClientId(Client client)throws  ServiceException
   {
       try {
           List<Reservation> reservationsList=this.reservationDao.findResaByClientId(client.getId());
           for (Reservation reservation:reservationsList)
           {
               reservation.setClient(clientService.findById(reservation.getClient_id()));
               reservation.setVehicule(vehicleService.findById(reservation.getVehicle_id()));
           }
           return reservationsList;
       } catch (DaoException e) {
           e.printStackTrace();
       }
       return null;
   }

   public int countReservationsForClient(Client client)
   {
       return this.reservationDao.reservationsCountForClient(client.getId());
   }


   public int count()
   {
       return this.reservationDao.count();
   }

   public int delete(int vehicle_id)
   {
       try {
       return  this.reservationDao.delete(vehicle_id);
       } catch (DaoException e) {
           e.printStackTrace();
       }
       return 0;
   }

    public long create(Reservation reservation) throws ServiceException {

        if(!reservationValidator.isValid(reservation))
            throw new ServiceException("ERREUR CREATING RESERVATION");

        try {
            return this.reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();

        }

    }


    public Reservation findById(int vehicle_id) throws  ServiceException
    {
        try {
            return this.reservationDao.findById(vehicle_id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public int update(Reservation reservation) throws ServiceException
    {
        if(!reservationValidator.isValid(reservation))
            throw new ServiceException("ERREUR UPDATING RESERVATION");
        try {
            return this.reservationDao.update(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Error updating reservation");
        }
    }








}
