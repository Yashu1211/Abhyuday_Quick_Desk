package controllers;

import models.Role;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/registerPage.do")
public class RegisterPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Role> roles = Role.getAllRoles();  
        request.setAttribute("roles", roles);   

        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}

