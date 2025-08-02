package models;

import java.sql.Timestamp;

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
}
