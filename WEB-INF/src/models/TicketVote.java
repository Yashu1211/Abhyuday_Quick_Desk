package models;

import java.sql.Timestamp;

public class TicketVote {
    private int voteId;
    private Ticket ticket;
    private User user;
    private VoteType voteType;
    private Timestamp votedAt;

    // Constructors
    public TicketVote() {

    }

    public TicketVote(int voteId, Ticket ticket, User user, VoteType voteType, Timestamp votedAt) {
        this.voteId = voteId;
        this.ticket = ticket;
        this.user = user;
        this.voteType = voteType;
        this.votedAt = votedAt;
    }

    // Getters and Setters
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
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

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Timestamp getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(Timestamp votedAt) {
        this.votedAt = votedAt;
    }
}
