package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.UserDao;
import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;


@Service
@Scope("singleton")


public class UserService implements UserDetailsService {


    @Autowired
    private UserDao userDao;


    public UserService(UserDao userDao)
    {
        this.userDao=userDao;
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return this.userDao.loadUserByUsername(username);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
            throw new UsernameNotFoundException("User not found");
    }
}
