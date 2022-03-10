package com.epf.rentmanager.ui.servlets.clientServlets;

import com.epf.rentmanager.dao.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


@WebServlet(urlPatterns = "/users/modify")

public class ClientModifyServlet extends HttpServlet {

    private static final String USER_MODIFY="/WEB-INF/views/users/modify.jsp";
    private static final String USERS_LIST="/users";

    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client=clientService.findById(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("client",client);
        this.getServletContext().getRequestDispatcher(USER_MODIFY).forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Client client= new Client(Integer.parseInt(req.getParameter("id")),
                req.getParameter("last_name"),
                req.getParameter("first_name"),
                req.getParameter("email"),
                LocalDate.parse(req.getParameter("date_de_naissance")));


        try {
            clientService.update(client);
            req.setAttribute("success",true);
            this.getServletContext().getRequestDispatcher(USERS_LIST).forward(req,resp);


        } catch (ServiceException e) {
            e.printStackTrace();
            req.setAttribute("success",false);
            this.doGet(req,resp);



        }



    }
}
