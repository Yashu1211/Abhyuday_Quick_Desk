package models;

import java.sql.Timestamp;

public class Notification {
    private int notificationId;
    private User user;
    private Ticket ticket;
    private String message;
    private boolean isRead;
    private Timestamp createdAt;

    // Constructors
    public Notification() {

    }

    public Notification(int notificationId, User user, Ticket ticket, String message, boolean isRead, Timestamp createdAt) {
        this.notificationId = notificationId;
        this.user = user;
        this.ticket = ticket;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
