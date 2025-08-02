package controllers;

import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        boolean success = user.login();

        if (success) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role (optional logic)
            int roleId = user.getRole().getRoleId();
            if (roleId == 1) {
                response.sendRedirect("admindashboard.jsp");
            } else if (roleId == 2) {
                response.sendRedirect("agentdashboard.jsp");
            } else {
                response.sendRedirect("userdashboard.jsp");
            }

        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}