package com.epf.rentmanager.ui.servlets.vehicleServlets;

import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet( urlPatterns = "/cars/delete")
public class VehicleDeleteServlet extends HttpServlet {
    private static final String LIST_VEHICLES="/cars";

    @Autowired
    private VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vehicleService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath()+LIST_VEHICLES);
    }
}
