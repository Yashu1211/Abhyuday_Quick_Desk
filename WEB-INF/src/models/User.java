package models;

import java.sql.Timestamp;
import utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors
    public User() {

    }

    public User(String name, String email, String password, int roleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = new Role();
        this.role.setRoleId(roleId);
    }

    public User(int userId, String name, String email, String password, Role role,
                Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean register() {
        String query = "INSERT INTO users (name, email, password, role_id, created_at, updated_at) " +
                     "VALUES (?, ?, ?, ?, NOW(), NOW())";
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, role.getRoleId());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User login(String email, String password) {
        String query = "SELECT u.user_id, u.name, u.email, u.password, u.role_id, r.role_name, u.created_at, u.updated_at " +
                       "FROM users u JOIN roles r ON u.role_id = r.role_id " +
                       "WHERE u.email = ? AND u.password = ?";

        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));

                return new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    role,
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );

            }   

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean login() {
        String query = "SELECT u.user_id, u.name, u.email, u.role_id, r.role_name " +
                        "FROM users u JOIN roles r ON u.role_id = r.role_id " +
                        "WHERE u.email = ? AND u.password = ?";
        try (Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.userId = rs.getInt("user_id");
                this.name = rs.getString("name");

                Role r = new Role();
                r.setRoleId(rs.getInt("role_id"));
                r.setRoleName(rs.getString("role_name"));
                this.setRole(r);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
