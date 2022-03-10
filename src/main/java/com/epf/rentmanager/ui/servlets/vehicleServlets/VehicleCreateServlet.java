package com.epf.rentmanager.ui.servlets.vehicleServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/cars/create")

public class VehicleCreateServlet extends HttpServlet {
    private static final String CREATE_VEHICLE="/WEB-INF/views/vehicles/create.jsp";



    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            getServletContext().getRequestDispatcher(CREATE_VEHICLE).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle v = new Vehicle( req.getParameter("manufacturer"),req.getParameter("modele"),Integer.parseInt(req.getParameter("seats")));

        try {
            vehicleService.create(v);
            req.setAttribute("success",true);
        } catch (ServiceException e) {
            e.printStackTrace();
            req.setAttribute("success",false);

        }
        getServletContext().getRequestDispatcher(CREATE_VEHICLE).forward(req,resp);

    }
}
