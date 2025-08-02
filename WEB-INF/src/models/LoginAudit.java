package models;

import java.sql.Timestamp;

public class LoginAudit {
    private int logId;
    private User user;
    private String action; // e.g.: LOGIN or LOGOUT
    private Timestamp timestamp;

    // Constructors
    public LoginAudit() {

    }

    public LoginAudit(int logId, User user, String action, Timestamp timestamp) {
        this.logId = logId;
        this.user = user;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
