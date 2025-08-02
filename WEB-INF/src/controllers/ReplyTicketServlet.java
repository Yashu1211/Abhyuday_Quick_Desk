package controllers;
import models.Ticket;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

@WebServlet("/replyticket.do")
public class ReplyTicketServlet extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    User agent = (session != null ? (User) session.getAttribute("user") : null);
    if (agent == null) {
      resp.sendRedirect("login.jsp");
      return;
    }
    int ticketId = Integer.parseInt(req.getParameter("ticketId"));
    String message = req.getParameter("message");
    String newStatus = req.getParameter("status");  // e.g. "RESOLVED"
    boolean ok = Ticket.addComment(ticketId, agent.getUserId(), message, newStatus);
    resp.sendRedirect("ticketdetail.do?id=" + ticketId);
  }
}
