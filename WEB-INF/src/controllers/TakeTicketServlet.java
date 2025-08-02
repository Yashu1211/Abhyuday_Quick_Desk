package controllers;
import models.Ticket;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

@WebServlet("/taketicket.do")
public class TakeTicketServlet extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    User agent = (session != null ? (User) session.getAttribute("user") : null);
    if (agent == null) {
      resp.sendRedirect("login.jsp");
      return;
    }
    int ticketId = Integer.parseInt(req.getParameter("ticketId"));
    boolean ok = Ticket.assignAgent(ticketId, agent.getUserId());
    resp.sendRedirect("agentdashboard.do");
  }
}
