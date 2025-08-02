package controllers;
import models.Ticket;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

@WebServlet("/ticketdetail.do")
public class TicketDetailServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    User agent = (session != null ? (User) session.getAttribute("user") : null);
    if (agent == null) {
      resp.sendRedirect("login.jsp");
      return;
    }
    int tid = Integer.parseInt(req.getParameter("id"));
    Ticket t = Ticket.getFullTicket(tid);
    req.setAttribute("ticket", t);
    req.getRequestDispatcher("agent-ticket-detail.jsp").forward(req, resp);
  }
}
