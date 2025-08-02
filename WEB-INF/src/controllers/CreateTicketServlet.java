package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import models.*;

@WebServlet("/create-ticket.do")
public class CreateTicketServlet extends HttpServlet {

    // 🟢 Show form (and populate dropdown)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categoryList = Category.getAllCategories();
        request.setAttribute("categories", categoryList);

        request.getRequestDispatcher("create_ticket.jsp").forward(request, response);
    }

    // 🔵 Handle form submit
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

    // Defensive check
        if (title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            response.sendRedirect("create_ticket.jsp?error=TitleOrDescriptionMissing");
            return;
        }

        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setUser(currentUser);
        ticket.setCategory(new Category(categoryId));

        TicketStatus status = TicketStatus.getStatusByName("OPEN");
        ticket.setStatus(status);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        boolean success = Ticket.createTicket(ticket);

        if (success) {
            response.sendRedirect("userdashboard.do?msg=TicketCreated");
        } else {
            response.sendRedirect("create_ticket.jsp?error=TicketCreationFailed");
        }
    }
}
