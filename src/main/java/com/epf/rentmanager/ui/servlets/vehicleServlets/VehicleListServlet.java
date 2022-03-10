package com.epf.rentmanager.ui.servlets.vehicleServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/cars",
name = "cars")

public class VehicleListServlet extends HttpServlet {

    private static final String VEHICLES_LIST ="/WEB-INF/views/vehicles/list.jsp";

    @Autowired
    private  VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List <Vehicle> vehicleList= vehicleService.findAll();
            req.setAttribute("vehicles",vehicleList);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher(VEHICLES_LIST).forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
