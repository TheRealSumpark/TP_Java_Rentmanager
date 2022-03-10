package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.utils.Validators.VehicleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class VehicleService {

	private VehicleDao vehicleDao;

	@Autowired
	VehicleValidator vehicleValidator;
	@Autowired
	ReservationDao reservationDao;

	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}


	public long create(Vehicle vehicle) throws ServiceException {

		try {
			if (!vehicleValidator.isVehicleValid(vehicle))
				throw new ServiceException("Vehicle not valid");

				return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		throw new ServiceException("Error: Creating vehicle");

	}

	public Vehicle findById(long id)  {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public int count()
	{
		try {
			return this.vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countVehiclesForClient(Client client)
	{
		try {
			return this.vehicleDao.countVehiclesForClient(client.getId());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(int  vehicle_id)
	{
		try {
			this.reservationDao.deleteReservationByVehicleId(vehicle_id);
			return this.vehicleDao.delete(vehicle_id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Vehicle vehicle) throws ServiceException
	{


		try {
			if (!vehicleValidator.isVehicleValid(vehicle))
				throw new ServiceException("Vehicle not valid");

			return this.vehicleDao.update(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}







	
}
