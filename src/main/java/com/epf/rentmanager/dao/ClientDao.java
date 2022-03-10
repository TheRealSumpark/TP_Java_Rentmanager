package com.epf.rentmanager.dao;


import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Scope("singleton")

public class ClientDao extends Throwable {
	


	private ClientDao() {}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email,naissance) VALUES(?, ?, ?,?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(*) as total FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client set nom=?,prenom=?,email=?,naissance=? WHERE id=?;";
	private static final String FIND_CLIENT_BY_EMAIL_QUERY="SELECT id,nom,prenom,email,naissance FROM Client WHERE email=?;";

	public int create(Client client) throws DaoException {
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_CLIENT_QUERY);
			pstm.setString(1,client.getNom());
			pstm.setString(2,client.getPrenom());
			pstm.setString(3, client.getEmail());
			pstm.setDate(4, Date.valueOf(client.getNaissance())); // convert LocalDate to SQLDate

			pstm.executeUpdate();
			return client.getId();
			} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}
	
	public int  delete(int client_id) throws DaoException {
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_CLIENT_QUERY);
			pstm.setInt(1,client_id);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public Optional<Client> findById(int id) throws DaoException {
			try {
				Connection conn = ConnectionManager.getConnection(); // creation d'une instance pour la connection avec la db
				PreparedStatement preparedStatement = conn.prepareStatement(FIND_CLIENT_QUERY); //
				preparedStatement.setInt(1,id); // param index starts from 1 not 0

				ResultSet clientsList = preparedStatement.executeQuery();
				//executeQuery()  returns a result set
				//ResultSet pour la récupération des resultats


				 clientsList.next();
					Client c = new Client(id,
							clientsList.getString("nom"),
							clientsList.getString("prenom"),
							clientsList.getString("email"),
							clientsList.getDate("naissance").toLocalDate());


					return Optional.of(c); //  or Optional.ofNullable()


			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {

		try {
			Connection conn  = ConnectionManager.getConnection();
			PreparedStatement  pstm = conn.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs= pstm.executeQuery();
			List<Client>clientsList = new ArrayList<>();

			while(rs.next())
			{
				clientsList.add(
						new Client(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getDate(5).toLocalDate()
						)
				);
			}

			return clientsList;


		} catch (SQLException e) {
			e.printStackTrace();

		}


		return null;

	}

	public int count()
	{
			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmp = conn.prepareStatement(COUNT_CLIENTS_QUERY);
				ResultSet rs= stmp.executeQuery();
				if (rs.next())
					return rs.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return  0;

	}


	public int update(Client client) throws DaoException
	{
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(UPDATE_CLIENT_QUERY);

			pstm.setString(1,client.getNom());
			pstm.setString(2,client.getPrenom());
			pstm.setString(3,client.getEmail());
			pstm.setDate(4, Date.valueOf(client.getNaissance()));
			pstm.setInt(5,client.getId());

			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}


	public Client findByEmail(String email) throws DaoException
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_CLIENT_BY_EMAIL_QUERY);
			pstm.setString(1,email);
			ResultSet rs = pstm.executeQuery();
			if (rs.next())
			{
				return new Client(rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getDate("naissance").toLocalDate()
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();

		}
		return null;
	}




}
