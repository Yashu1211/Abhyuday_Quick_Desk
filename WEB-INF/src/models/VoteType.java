package models;

public class VoteType {
    private int voteTypeId;
    private String voteName;  // UPVOTE or DOWNVOTE

    // Constructors
    public VoteType() {

    }

    public VoteType(int voteTypeId, String voteName) {
        this.voteTypeId = voteTypeId;
        this.voteName = voteName;
    }

    // Getters and Setters
    public int getVoteTypeId() {
        return voteTypeId;
    }

    public void setVoteTypeId(int voteTypeId) {
        this.voteTypeId = voteTypeId;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }
}
