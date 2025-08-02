package models;

import java.sql.Timestamp;

public class TicketComment {
    private int commentId;
    private Ticket ticket;
    private User user;
    private String message;
    private Timestamp createdAt;

    // Constructors
    public TicketComment() {
        
    }

    public TicketComment(int commentId, Ticket ticket, User user, String message, Timestamp createdAt) {
        this.commentId = commentId;
        this.ticket = ticket;
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
