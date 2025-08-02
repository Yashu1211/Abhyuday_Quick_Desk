package models;

import java.sql.Timestamp;
import utils.DBConnect;
import utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;


public class Ticket {
    private int ticketId;
    private String title;
    private String description;
    private User user;        // The person who raised the ticket
    private User agent;       // The assigned support agent (can be null)
    private Category category;
    private TicketStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String categoryName;
    private String statusName;
    private int userId;
    private int agentId;
    private String priority;



    // Constructors
    public Ticket() {
        
    }

    public Ticket(int ticketId, String title, String description, User user, User agent,
                  Category category, TicketStatus status,
                  Timestamp createdAt, Timestamp updatedAt) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.user = user;
        this.agent = agent;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

     public static boolean createTicket(Ticket ticket) {
        boolean success = false;
        String query = "INSERT INTO tickets (title, description, user_id, agent_id, category_id, status_id, created_at, updated_at) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, ticket.getTitle());
            ps.setString(2, ticket.getDescription());
            ps.setInt(3, ticket.getUser().getUserId());

            if (ticket.getAgent() != null) {
                ps.setInt(4, ticket.getAgent().getUserId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setInt(5, ticket.getCategory().getCategoryId());
            ps.setInt(6, ticket.getStatus().getStatusId());
            ps.setTimestamp(7, ticket.getCreatedAt());
            ps.setTimestamp(8, ticket.getUpdatedAt());

            int rows = ps.executeUpdate();
            success = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }


    public static TicketStatus getStatusByName(String statusName) {
        TicketStatus status = null;
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ticket_statuses WHERE status_name = ?")) {
            ps.setString(1, statusName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = new TicketStatus();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatusName(rs.getString("status_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<Ticket> getTicketsByUser(int userId) {
            List <Ticket> tickets = new ArrayList<>();
            try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM tickets WHERE user_id = ? ORDER BY created_at DESC")) {
                
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Ticket t = new Ticket();
                    t.setTicketId(rs.getInt("ticket_id"));
                    t.setTitle(rs.getString("title"));
                    t.setDescription(rs.getString("description"));
                    t.setCreatedAt(rs.getTimestamp("created_at"));
                    t.setUpdatedAt(rs.getTimestamp("updated_at"));

                    Category cat = Category.getCategoryById(rs.getInt("category_id"));
                    t.setCategory(cat);

                    TicketStatus stat = TicketStatus.getStatusById(rs.getInt("status_id"));
                    t.setStatus(stat);

                    tickets.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return tickets;
    }


    public static List<Ticket> getUnassignedTickets() {
        List<Ticket> list = new ArrayList<>();
        String sql ="SELECT t.ticket_id, t.title, c.name AS categoryName, s.status_name, t.created_at, t.updated_at " +
                    "FROM tickets t " +
                    "JOIN categories c ON c.category_id = t.category_id " +
                    "JOIN ticket_statuses s ON s.status_id = t.status_id " +
                    "WHERE t.agent_id IS NULL AND s.status_name IN ('OPEN', 'IN_PROGRESS') " +
                    "ORDER BY t.created_at DESC";
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setTicketId(rs.getInt("ticket_id"));
                t.setTitle(rs.getString("title"));
                t.setCategoryName(rs.getString("categoryName"));
                t.setStatusName(rs.getString("status_name"));
                t.setCreatedAt(rs.getTimestamp("created_at"));
                t.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(t);
            }
        } catch (SQLException x) { x.printStackTrace(); }
        return list;
    }

  // b) fetch tickets assigned to a given agent:
    public static List<Ticket> getTicketsByAgent(int agentId) {
        List<Ticket> list = new ArrayList<>();
        String sql ="SELECT t.ticket_id, t.title, c.name AS categoryName, s.status_name, t.updated_at " +
                    "FROM tickets t " +
                    "JOIN categories c ON c.category_id = t.category_id " +
                    "JOIN ticket_statuses s ON s.status_id = t.status_id " +
                    "WHERE t.agent_id = ? " +
                    "ORDER BY t.updated_at DESC";
            try (Connection conn = DBConnect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, agentId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Ticket t = new Ticket();
                        t.setTicketId(rs.getInt("ticket_id"));
                        t.setTitle(rs.getString("title"));
                        t.setCategoryName(rs.getString("categoryName"));
                        t.setStatusName(rs.getString("status_name"));
                        t.setUpdatedAt(rs.getTimestamp("updated_at"));
                        list.add(t);
                    }
                }
            } catch (SQLException x) { x.printStackTrace(); }
                return list;
    }  
           

  // c) Fetch full ticket + comments for detail view:
    public static Ticket getFullTicket(int ticketId) {
        String sql ="SELECT t.*, u.name AS creatorName, c.name AS categoryName, s.status_name " +
                    "FROM tickets t " +
                    "JOIN users u ON u.user_id = t.user_id " +
                    "JOIN categories c ON c.category_id = t.category_id " +
                    "JOIN ticket_statuses s ON s.status_id = t.status_id " +
                    "WHERE t.ticket_id = ?";
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticketId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ticket t = new Ticket();
                        t.setTicketId(rs.getInt("ticket_id"));
                        t.setTitle(rs.getString("title"));
                        t.setDescription(rs.getString("description"));
                        t.setUserId(rs.getInt("user_id"));
                        t.setAgentId(rs.getInt("agent_id"));
                        t.setCategoryName(rs.getString("categoryName"));
                        t.setStatusName(rs.getString("status_name"));
                        t.setCreatedAt(rs.getTimestamp("created_at"));
                        t.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return t;
                }
            }
        } catch (SQLException x) { x.printStackTrace(); }
            return null;
    }

  // d) Agent takes ownership of a ticket:
  public static boolean assignAgent(int ticketId, int agentId) {
        String sql = "UPDATE tickets SET agent_id=?, status_id=(SELECT status_id FROM ticket_statuses WHERE status_name='IN_PROGRESS') WHERE ticket_id=? AND agent_id IS NULL";
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, agentId);
            ps.setInt(2, ticketId);
            return (ps.executeUpdate() == 1);
        } catch (SQLException x) { x.printStackTrace(); }
        return false;
    }

  // e) Add agent reply/comment:
  public static boolean addComment(int ticketId, int userId, String message, String newStatus) {
        String insertComment = "INSERT INTO ticket_comments(ticket_id, user_id, message) VALUES(?,?,?)";
        String updateStatus = "UPDATE tickets SET status_id=(SELECT status_id FROM ticket_statuses WHERE status_name=?) WHERE ticket_id=?";
        try (Connection conn = DBConnect.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement p1 = conn.prepareStatement(insertComment);
                PreparedStatement p2 = conn.prepareStatement(updateStatus)) {
                p1.setInt(1, ticketId);
                p1.setInt(2, userId);
                p1.setString(3, message);
                p1.executeUpdate();

                p2.setString(1, newStatus);
                p2.setInt(2, ticketId);
                p2.executeUpdate();

                conn.commit();
                return true;
            }
        } catch (SQLException x) {
            x.printStackTrace();
            return false;
        }
    }

    public static TicketStatus getStatusById(int id) {
        TicketStatus status = null;

        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ticket_statuses WHERE status_id = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    status = new TicketStatus();
                    status.setStatusId(rs.getInt("status_id"));
                    status.setStatusName(rs.getString("status_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }




    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

        public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}
