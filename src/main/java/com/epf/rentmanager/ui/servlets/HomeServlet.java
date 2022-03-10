package com.epf.rentmanager.ui.servlets;



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


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final String HOME = "/WEB-INF/views/home.jsp";

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int nmbVehicles=vehicleService.count();


        req.setAttribute("nmbVehicles",nmbVehicles);
        req.setAttribute("nmbReservations", reservationService.count());
        req.setAttribute("nmbClients", clientService.count());

        getServletContext().getRequestDispatcher(HOME).forward(req, resp);



    }
}
