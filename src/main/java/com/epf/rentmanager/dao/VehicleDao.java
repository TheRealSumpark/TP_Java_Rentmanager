package com.epf.rentmanager.dao;

import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Scope("singleton")
public class VehicleDao  {
	

	private VehicleDao() {}


	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur,modele, nb_places) VALUES(?,?,?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY= "SELECT COUNT(*)  as total FROM Vehicle;";
	private static final String COUNT_VEHICLES_FOR_CLIENT_QUERY="SELECT   COUNT(DISTINCT v.id)  as total from Vehicle v INNER JOIN Reservation r ON r.vehicle_id=v.id WHERE  r.client_id=?;";
	private static final String UPDATE_VEHICLE_QUERY ="UPDATE Vehicle set constructeur=?, modele=?, nb_places=? WHERE id=?;";

	public int create(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_VEHICLE_QUERY);

			pstm.setString(1, vehicle.getConstructeur());
			pstm.setString(2, vehicle.getModele());
			pstm.setInt(3,vehicle.getNb_places());

			return pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstm.setInt(1,vehicle.getId());

			return pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Vehicle> findById(long id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_VEHICLE_QUERY);
			pstm.setLong(1,id);
			ResultSet rs=pstm.executeQuery();

			if(rs.next())
			{
				 Vehicle v= new Vehicle(rs.getInt("id"),rs.getString("constructeur"),rs.getString("modele"),rs.getInt("nb_places"));
				 return Optional.of(v);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs=pstm.executeQuery();

			List<Vehicle> vehiclesList= new ArrayList<>();
			while(rs.next())
			{
				vehiclesList.add(new Vehicle(
						rs.getInt("id"),
						rs.getString("constructeur"),
						rs.getString("modele"),
						rs.getInt("nb_places")
				));
			}
			return vehiclesList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws DaoException
	{
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet rs= pstm.executeQuery();

			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0 ;
	}

	public int countVehiclesForClient(int  id_client) throws DaoException
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(COUNT_VEHICLES_FOR_CLIENT_QUERY);
			pstm.setInt(1,id_client);
			ResultSet rs= pstm.executeQuery();
			if(rs.next())
				return rs.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int  delete(int vehicle_id) throws DaoException {
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstm.setInt(1,vehicle_id);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}


	public int update(Vehicle vehicle) throws DaoException
	{
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstm =conn.prepareStatement(UPDATE_VEHICLE_QUERY);

			pstm.setString(1,vehicle.getConstructeur());
			pstm.setString(2,vehicle.getModele());
			pstm.setInt(3,vehicle.getNb_places());
			pstm.setInt(4,vehicle.getId());

			return pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}
}
