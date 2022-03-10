package com.ensta.rentmanager;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.utils.Validators.VehicleValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTest {

    @InjectMocks
    VehicleValidator vehicleValidator;


    @Test
    public void isVehicleValid_should_return_true_when_vehicle_is_valid ()
    {
        //Given
        Vehicle vehicle = new Vehicle("Tesla","X",2);
        //then
        assertTrue(vehicleValidator.isVehicleValid(vehicle));

    }

    @Test
    public void isVehicleValid_should_return_false_when_vehicle_is_not_valid ()
    {
        //Given
        Vehicle vehicle = new Vehicle("Tesla","",9);
        //then
        assertFalse(vehicleValidator.isVehicleValid(vehicle));

    }










}
