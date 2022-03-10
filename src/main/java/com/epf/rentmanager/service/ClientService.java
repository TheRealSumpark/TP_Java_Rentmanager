package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.utils.Validators.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	@Autowired
	private ClientValidator clientValidator;
	@Autowired
	private ReservationDao reservationDao;


	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	
	public long create(Client client) throws ServiceException {

		try {
			if(clientValidator.isValid(client) && !clientValidator.emailAlreadyTaken(client))
			return this.clientDao.create(client);

		} catch (DaoException e) {
			e.printStackTrace();

		}
		throw new ServiceException("Client not valid ");
	}

	public Client findById(int id)  {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}

	}

	public int deleteClient(int client_id) throws  ServiceException
	{
		try {
			reservationDao.deleteReservationByClientId(client_id);
			return this.clientDao.delete(client_id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		throw new ServiceException("Error deleting Client");
	}

	public int count()
	{
		return this.clientDao.count();
	}


	public int update(Client client) throws ServiceException
	{
		try {
			if(clientValidator.isValid(client))
				return clientDao.update(client);

		} catch (DaoException e) {
			e.printStackTrace();

		}
		throw new ServiceException("Error updating client");
	}


	public Client findByEmail(String email) throws ServiceException
	{
		try {
		return this.clientDao.findByEmail(email);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding Client by email");
		}
	}



	
}
