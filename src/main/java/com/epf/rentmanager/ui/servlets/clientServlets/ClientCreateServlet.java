package com.epf.rentmanager.ui.servlets.clientServlets;


import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/create")

public class ClientCreateServlet extends HttpServlet {
    private static final String USER_CREATE="/WEB-INF/views/users/create.jsp";
    private static final String USERS_CREATE="/users/create";

    @Autowired
    private ClientService clientService;

     @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(USER_CREATE).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Client client= new Client(req.getParameter("first_name"),
                    req.getParameter("last_name"),
                    req.getParameter("email"),
                    LocalDate.parse(req.getParameter("date_de_naissance")));

        try {
            clientService.create(client);
            req.setAttribute("success",true);
        } catch (ServiceException e) {
            e.printStackTrace();
            req.setAttribute("success",false);

        }

        this.getServletContext().getRequestDispatcher(USER_CREATE).forward(req,resp);


    }
}
