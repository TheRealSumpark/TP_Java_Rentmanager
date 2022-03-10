package com.epf.rentmanager.ui.servlets.reservationServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/rents/modify")
public class ReservationModifyServlet extends HttpServlet {

    private static final String RENTS_MODIFY="/WEB-INF/views/rents/modify.jsp";
    private static final String RENTS_LIST ="/rents";

    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Reservation reservation = reservationService.findById(Integer.parseInt(req.getParameter("id")));
            List <Client> clientsList= clientService.findAll();
            List <Vehicle> vehiclesList = vehicleService.findAll();

            req.setAttribute("clients",clientsList);
            req.setAttribute("vehicles",vehiclesList);
            req.setAttribute("rent",reservation);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher(RENTS_MODIFY).forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Reservation reservation= new Reservation(Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("client")),
                Integer.parseInt(req.getParameter("car")),
                LocalDateTime.parse(req.getParameter("begin")),
                LocalDateTime.parse(req.getParameter("end"))
        );

        try {
            this.reservationService.update(reservation);


            req.setAttribute("success",true);
            this.getServletContext().getRequestDispatcher(RENTS_LIST).forward(req,resp);

        } catch (ServiceException e) {
            e.printStackTrace();
            req.setAttribute("success",false);
            this.doGet(req,resp);


        }



    }
}
