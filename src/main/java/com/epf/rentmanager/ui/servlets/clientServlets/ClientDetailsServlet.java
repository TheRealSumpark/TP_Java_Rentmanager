package com.epf.rentmanager.ui.servlets.clientServlets;

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
import java.util.*;

@WebServlet(urlPatterns = "/users/details")

public class ClientDetailsServlet extends HttpServlet {


    private static final String USER_DETAILS="/WEB-INF/views/users/details.jsp";

    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Client client = clientService.findById(Integer.parseInt(req.getParameter("id")));

        List<Reservation> reservationsList= null;
        try {
            reservationsList = reservationService.findResaByClientId(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        List<Vehicle> vehiclesList= new ArrayList<>();


            for(Reservation r : reservationsList)
            {
                if (!vehiclesList.contains(r.getVehicle()))
                    vehiclesList.add(r.getVehicle());
            }

        req.setAttribute("client",client);
        req.setAttribute("rents",reservationsList);
        req.setAttribute("vehicles",vehiclesList);
        req.setAttribute("nmb_vehicles",vehicleService.countVehiclesForClient(client));
        req.setAttribute("nmb_reservations",reservationService.countReservationsForClient(client));

        this.getServletContext().getRequestDispatcher(USER_DETAILS).forward(req,resp);

    }
}
