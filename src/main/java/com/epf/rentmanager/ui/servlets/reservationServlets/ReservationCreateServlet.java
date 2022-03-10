package com.epf.rentmanager.ui.servlets.reservationServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {
    private static final String RENT_CREATE="/WEB-INF/views/rents/create.jsp";

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Client> clientList=clientService.findAll();
            req.setAttribute("clients",clientList);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            List<Vehicle>vehicleList= vehicleService.findAll();
            req.setAttribute("vehicles",vehicleList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        this.getServletContext().getRequestDispatcher(RENT_CREATE).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            reservationService.create(new Reservation(Integer.parseInt(req.getParameter("client")),
                    Integer.parseInt(req.getParameter("car")),
                    LocalDateTime.parse(req.getParameter("begin")),
                    LocalDateTime.parse(req.getParameter("end"))
            ));
            req.setAttribute("success",true);

        } catch (ServiceException e) {
            e.printStackTrace();
            req.setAttribute("success",false);

        }
        this.doGet(req,resp);

    }
}



