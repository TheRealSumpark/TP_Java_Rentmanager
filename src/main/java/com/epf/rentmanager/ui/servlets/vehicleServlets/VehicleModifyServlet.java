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

    @WebServlet(urlPatterns = "/cars/modify")

public class VehicleModifyServlet extends HttpServlet {

        private static final String CARS_MODIFY="/WEB-INF/views/vehicles/modify.jsp";
        private static final String CARS_LIST="/cars";

    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = vehicleService.findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("vehicle",vehicle);
            this.getServletContext().getRequestDispatcher(CARS_MODIFY).forward(req,resp);

    }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Vehicle vehicle = new Vehicle(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("manufacturer"),
                    req.getParameter("modele"),
                    Integer.parseInt(req.getParameter("seats")
                    ));

            try {
                vehicleService.update(vehicle);
                req.setAttribute("success", true);
                getServletContext().getRequestDispatcher(CARS_LIST).forward(req, resp);

            } catch (ServiceException e) {
                e.printStackTrace();
                req.setAttribute("success", false);
                this.doGet(req,resp);
            }

        }
    }
