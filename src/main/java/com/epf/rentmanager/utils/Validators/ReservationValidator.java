package com.epf.rentmanager.utils.Validators;


import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
@Scope("singleton")
public class ReservationValidator {

    @Autowired
    ClientService clientService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationDao reservationDao;



    public boolean isValid(Reservation reservation) throws ServiceException {

        return !isVehicle_rent_for_more_than_seven_days_by_same_user(reservation) &&
                !this.isVehicle_rent_for_more_than_thirty_days_without_rest(reservation) &&
                !this.isVehicle_rent_twice_same_day(reservation)&&
                 reservation.getDebut().compareTo(reservation.getFin()) < 0;
    }



    public boolean isVehicle_rent_twice_same_day(Reservation reservation) throws ServiceException
    {

        try {
            return this.reservationDao.isVehicleRent(reservation);

        } catch (DaoException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }

    public boolean isVehicle_rent_for_more_than_seven_days_by_same_user(Reservation reservation)
    {
        return Duration.between(reservation.getDebut(),reservation.getFin()).toDays()>7;

    }

    /**
     * Renvoie true si le nombre jours consécutif pour la réservation d'une voiture est supérieur à 30
     * @param  reservation L'instance de la réservation à tester
     * @return Resultat du test (>30)
     */
    public boolean isVehicle_rent_for_more_than_thirty_days_without_rest(Reservation reservation) throws ServiceException {
        try {

            // Récupération de la liste des réservations du même véhicule
            List<Reservation> reservationsList=reservationDao.findReservationsOfSpecificVehicle(reservation);
            reservationsList.add(reservation);

            //Ordonnancement par ordre croissant de la liste ( critère = date de début de réservation )
            reservationsList.sort((Reservation r, Reservation o)->{  return  reservation.getDebut().compareTo(o.getDebut());});

            int total_consecutive_days=0;

            for (int i = 0 ; i < reservationsList.size();i++)
            {
                //Calcul de la période pour une réservation
                Reservation current_reservation = reservationsList.get(i);
                total_consecutive_days +=Duration.between(current_reservation.getDebut(),current_reservation.getFin()).toDays();

                if (total_consecutive_days > 30)
                    return true;

                if (i < reservationsList.size() -1) // Vérification de l'existence d'un élément suivant dans le tableau
                {
                    Reservation next_reservation = reservationsList.get(i+1);

                    //Vérification de la période entre deux réservations -> voir s'il n'y a pas de pause
                    long period_between_two_reservations=Duration.between(current_reservation.getFin(),next_reservation.getDebut()).toDays();

                    if(period_between_two_reservations <= 1)
                        total_consecutive_days ++;

                    if ( total_consecutive_days > 30)
                        return true;
                }
            }

            return false;

        } catch (DaoException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }



}
