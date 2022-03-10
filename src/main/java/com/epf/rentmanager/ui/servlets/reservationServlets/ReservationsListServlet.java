package com.epf.rentmanager.ui.servlets.reservationServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
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

@WebServlet(value = "/rents",name = "list_rents")

public class ReservationsListServlet extends HttpServlet {

    private static final String RENTS_LIST ="/WEB-INF//views/rents/list.jsp";

    @Autowired
    private ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Reservation> reservationList= null;
        try {
            reservationList = reservationService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("rents",reservationList);
        this.getServletContext().getRequestDispatcher(RENTS_LIST).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
