package models;

import java.sql.Timestamp;

public class TicketAttachment {
    private int attachmentId;
    private Ticket ticket;
    private String fileName;
    private String filePath;
    private Timestamp uploadedAt;

    // Constructors
    public TicketAttachment() {
        
    }

    public TicketAttachment(int attachmentId, Ticket ticket, String fileName, String filePath, Timestamp uploadedAt) {
        this.attachmentId = attachmentId;
        this.ticket = ticket;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    // Getters and Setters
    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
