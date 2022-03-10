package com.epf.rentmanager.dao;

import com.epf.rentmanager.dao.exception.DaoException;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.naming.spi.ResolveResult;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;



@Repository
@Scope("singleton")
public class ReservationDao {


	private ReservationDao() {}



	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_FOR_CLIENT_QUERY="SELECT COUNT(*) as total FROM Reservation WHERE client_id=?";
	private static final String COUNT_RESERVATIONS_QUERY="SELECT COUNT(*) as total FROM Reservation";
	private static final String FIND_RESERVATION_BY_ID_QUERY= "SELECT id,client_id,vehicle_id,debut,fin FROM Reservation WHERE  id=?;";
	private static final String UPDATE_RESERVATION_QUERY="UPDATE Reservation SET client_id=?,vehicle_id=?,debut=?,fin=? WHERE id=?;";
	private static final String FIND_RENT_VEHICLE_BY_DATE="SELECT count(*) as total from Reservation WHERE vehicle_id=? AND CAST(debut as date )= CAST (? as date);";
	private static final String FIND_RESERVATIONS_OF_SPECIFIC_VEHICLE_QUERY= "SELECT id, client_id,vehicle_id,debut,fin FROM Reservation WHERE vehicle_id=?;";

	private static final String DELETE_RESERVATION_BY_CLIENT_ID_QUERY = "DELETE FROM Reservation WHERE client_id=?;";
	private static final String DELETE_RESERVATION_BY_VEHICLE_ID_QUERY = "DELETE FROM Reservation WHERE vehicle_id=?;";

	public long create(Reservation reservation) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_RESERVATION_QUERY);
			pstm.setInt(1,reservation.getClient_id());
			pstm.setInt(2,reservation.getVehicle_id());
			pstm.setTimestamp(3,Timestamp.valueOf(reservation.getDebut()));
			pstm.setTimestamp(4,Timestamp.valueOf(reservation.getFin()));

			return pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	public long delete(Reservation reservation) throws DaoException {


		try {
			Connection conn  = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			pstm.setInt(1,reservation.getId());
			return pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}



		return 0;

	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstm.setLong(1,clientId);

			ResultSet rs= pstm.executeQuery();
			List<Reservation> reservationsList = new ArrayList<>();

			while (rs.next())
			{
				reservationsList.add(new Reservation(rs.getInt("id"),
						clientId,
						rs.getInt("vehicle_id"),
						rs.getTimestamp("debut").toLocalDateTime(),
						rs.getTimestamp("fin").toLocalDateTime()));
			}
			return reservationsList;
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;

	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstm.setLong(1,vehicleId);

			ResultSet rs= pstm.executeQuery();
			List<Reservation> reservationsList = new ArrayList<>();

			Reservation reservation = new Reservation();
			if (rs.next())
			{
				reservation.setClient_id(rs.getInt("client_id"));
				reservation.setVehicle_id(vehicleId);
				reservation.setDebut(rs.getTimestamp("debut").toLocalDateTime());
				reservation.setFin(rs.getTimestamp("fin").toLocalDateTime());
				reservationsList.add(reservation);
			}
			return reservationsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Reservation> findAll() throws DaoException {

		Connection conn= null;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs= pstmt.executeQuery();
			List<Reservation> reservationsList = new ArrayList<>();

			while (rs.next())
			{
					reservationsList.add(new Reservation(rs.getInt("id"),
							rs.getInt("client_id"),
							rs.getInt("vehicle_id"),
							rs.getTimestamp("debut").toLocalDateTime(),
							rs.getTimestamp("fin").toLocalDateTime()
							));

			}
			return reservationsList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw  new DaoException();
		}

	}


	public int reservationsCountForClient(int client_id)
	{
		Connection conn= null;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(COUNT_RESERVATIONS_FOR_CLIENT_QUERY);
			pstm.setInt(1,client_id);
			ResultSet rs=pstm.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public int count()
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmp = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs= stmp.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  0;
	}


	public int delete(int reservation_id) throws  DaoException
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			pstm.setInt(1,reservation_id);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public Reservation findById(int reservation_id) throws DaoException
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm =conn.prepareStatement(FIND_RESERVATION_BY_ID_QUERY);
			pstm.setInt(1,reservation_id);
			ResultSet rs = pstm.executeQuery();

			if(rs.next())
			{
				return new Reservation(rs.getInt("id"),
						rs.getInt("client_id"),
						rs.getInt("vehicle_id"),
						rs.getTimestamp("debut").toLocalDateTime(),
						rs.getTimestamp("fin").toLocalDateTime()
				);
			}


		} catch (SQLException e) {
			e.printStackTrace();

		}
		throw  new DaoException();
	}

	public int update(Reservation reservation)throws DaoException
	{
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(UPDATE_RESERVATION_QUERY);
			pstm.setInt(1,reservation.getClient_id());
			pstm.setInt(2,reservation.getVehicle_id());
			pstm.setTimestamp(3,Timestamp.valueOf(reservation.getDebut()));
			pstm.setTimestamp(4,Timestamp.valueOf(reservation.getFin()));
			pstm.setInt(5,reservation.getId());

			return pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}



	public boolean isVehicleRent(Reservation reservation ) throws DaoException
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_RENT_VEHICLE_BY_DATE);
			pstm.setInt(1,reservation.getVehicle_id());
			pstm.setTimestamp(2,Timestamp.valueOf(reservation.getDebut()));

			ResultSet rs= pstm.executeQuery();
			if(rs.next())
			{
				int res = rs.getInt("total");
				System.out.println(res);
				return res>0;

			}
			return false;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new DaoException();
	}


	public List<Reservation> findReservationsOfSpecificVehicle(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(FIND_RESERVATIONS_OF_SPECIFIC_VEHICLE_QUERY);
			pstm.setInt(1,reservation.getVehicle_id());
			ResultSet rs = pstm.executeQuery();
			List<Reservation> reservationsList = new ArrayList<>();

			while(rs.next())
			{
				reservationsList.add(new Reservation(
						rs.getInt("id"),
						rs.getInt("client_id"),
						rs.getInt("vehicle_id"),
						rs.getTimestamp("debut").toLocalDateTime(),
						rs.getTimestamp("fin").toLocalDateTime())
				);

			}
			return reservationsList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new DaoException();
	}



	public int deleteReservationByClientId(int client_id)
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_RESERVATION_BY_CLIENT_ID_QUERY);
			pstm.setInt(1,client_id);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public int deleteReservationByVehicleId(int vehicle_id)
	{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstm = conn.prepareStatement(DELETE_RESERVATION_BY_VEHICLE_ID_QUERY);
			pstm.setInt(1,vehicle_id);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
