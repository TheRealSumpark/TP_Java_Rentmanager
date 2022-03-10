package com.ensta.rentmanager;


import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.utils.Validators.ReservationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ReservationTest {

    @InjectMocks
    ReservationService reservationService;
    @Mock
    ReservationDao reservationDao;
    @InjectMocks
    ReservationValidator reservationValidator;


    @Test
    public void isVehicle_rent_twice_same_day_should_return_true_when_vehicle_is_already_rent_same_day() throws ServiceException, DaoException {
        //Given
        Reservation reservation = new Reservation(1,1, LocalDateTime.of(1998,4,4,20,15),LocalDateTime.now());


        when(reservationDao.isVehicleRent(reservation)).thenReturn(true);

        //Then
        assertTrue(reservationValidator.isVehicle_rent_twice_same_day(reservation));
    }


    @Test
    public void isVehicle_rent_twice_same_day_should_return_false_when_vehicle_is_not_already_rent_same_day() throws ServiceException {
        //Given
        Reservation reservation= new Reservation(1,1, LocalDateTime.now(),LocalDateTime.now());

        //Then
        assertFalse(reservationValidator.isVehicle_rent_twice_same_day(reservation));
    }


    @Test
    public void isVehicle_rent_for_more_than_seven_days_by_same_user_should_return_true_when_period_is_over_seven_days() throws ServiceException {
        //Given
        Reservation reservation= new Reservation(1,1, LocalDateTime.now(),LocalDateTime.now().plusDays(8));

        //Then
        assertTrue(reservationValidator.isVehicle_rent_for_more_than_seven_days_by_same_user(reservation));
    }

    @Test
    public void isVehicle_rent_for_more_than_seven_days_by_same_user_should_return_false_when_period_is_not_over_seven_days() throws ServiceException {
        //Given
        Reservation reservation= new Reservation(1,1, LocalDateTime.now(),LocalDateTime.now().plusDays(7));

        //Then
        assertFalse(reservationValidator.isVehicle_rent_for_more_than_seven_days_by_same_user(reservation));
    }


    @Test
    public void isVehicle_rent_for_more_than_thirty_days_without_rest_should_fail_when_dao_throws_exception() throws DaoException {
        //Given
        Reservation reservation= new Reservation(1,1, LocalDateTime.now(),LocalDateTime.now().plusDays(1));

        when(reservationDao.findReservationsOfSpecificVehicle(reservation)).thenThrow(DaoException.class);
        //Then
        assertThrows(ServiceException.class,()-> reservationValidator.isVehicle_rent_for_more_than_thirty_days_without_rest(reservation));


    }



}
