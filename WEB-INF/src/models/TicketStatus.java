package models;

import utils.DBConnect;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TicketStatus {
    private int statusId;
    private String statusName;

    // Constructors
    public TicketStatus() {
        
    }

    public TicketStatus(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public static TicketStatus getStatusByName(String name) {
        TicketStatus status = null;
        try {
            Connection conn = DBConnect.getConnection();
            String query = "SELECT * FROM ticket_statuses WHERE status_name = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = new TicketStatus(
                    rs.getInt("status_id"),
                    rs.getString("status_name")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static TicketStatus getStatusById(int statusId) {
        TicketStatus status = null;

        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ticket_statuses WHERE status_id = ?")) {
            
            ps.setInt(1, statusId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    status = new TicketStatus();
                    status.setStatusId(rs.getInt("status_id"));
                    status.setStatusName(rs.getString("name"));
                
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }


    

    // Getters and Setters
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
