package com.epf.rentmanager.ui.servlets.reservationServlets;

import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/rents/delete")
public class ReservationDeleteServlet  extends HttpServlet {

    private static final String LIST_RESERVATIONS="/rents";

    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservationService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath()+LIST_RESERVATIONS);
    }
}
