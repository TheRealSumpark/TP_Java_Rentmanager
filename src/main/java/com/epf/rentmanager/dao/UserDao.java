package com.epf.rentmanager.dao;

import com.epf.rentmanager.dao.exception.ServiceException;

import com.epf.rentmanager.model.User;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Scope("singleton")
public class UserDao {


    private  UserDao()
    {

    }

    private static final String FIND_USER_BY_USERNAME_QUERY="SELECT id,username,password,role from User WHERE username=?;";


    public User loadUserByUsername(String username) throws ServiceException
    {

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(FIND_USER_BY_USERNAME_QUERY);
            pstm.setString(1,username);
            ResultSet rs = pstm.executeQuery();
            if (rs.next())
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"));


        } catch (SQLException e) {
            e.printStackTrace();

        }

        throw new ServiceException("User not found");
    }


}
