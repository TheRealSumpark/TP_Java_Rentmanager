package com.epf.rentmanager.utils.Validators;

import com.epf.rentmanager.model.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("singleton")
public class VehicleValidator {



    /**
     * Renvoie true si le constructeur et le modele ne sont pas vides et si le nombre de places est entre (2 et 9)
     * @param vehicle L'instance du véhicule  à tester
     *
     */

    public boolean isVehicleValid(Vehicle vehicle)
    {
        if (vehicle.getConstructeur().isBlank())
            return false;
        if (vehicle.getModele().isBlank())
            return false;
        if (vehicle.getNb_places()<2 || vehicle.getNb_places()>9)
            return false;
        return true;
    }
}
