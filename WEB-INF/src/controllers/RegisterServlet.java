package controllers;

import models.User;
import models.Role;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleIdStr = request.getParameter("role_id");

        List<Role> roles = Role.getAllRoles();
        request.setAttribute("roles", roles);

        // If form not submitted yet, just show the page with roles
        if (name == null || email == null || password == null || roleIdStr == null) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // If form was submitted
        int roleId = Integer.parseInt(roleIdStr);
        User user = new User(name, email, password, roleId);
        boolean success = user.register();

        if (success) {
            response.sendRedirect("login.jsp?msg=registered");
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
