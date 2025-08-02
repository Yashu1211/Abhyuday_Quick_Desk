package controllers;
import models.Ticket;
import models.Ticket;
import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/agentdashboard.do")
public class AgentDashboardServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    User agent = (session != null ? (User) session.getAttribute("user") : null);
    if (agent == null || agent.getRole().getRoleName().equals("END_USER")) {
      resp.sendRedirect("login.jsp?error=unauthorized");
      return;
    }
    int agentId = agent.getUserId();
    List<Ticket> open = Ticket.getUnassignedTickets();
    List<Ticket> mine = Ticket.getTicketsByAgent(agentId);
    req.setAttribute("openTickets", open);
    req.setAttribute("myTickets", mine);
    req.getRequestDispatcher("agentdashboard.jsp").forward(req, resp);
  }
}
