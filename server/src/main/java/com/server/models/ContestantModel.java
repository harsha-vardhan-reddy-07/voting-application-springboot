package com.server.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "contestants")
public class ContestantModel {
    public String _id;
    public String electionId;
    public String contestantId;
    public String contestantName;
    public String contestantEmail;
    public String userId;
    public String organizationId;
    public String organization;
    public String status = "pending";
    public List<String> votes = new ArrayList<String>();
    public Number votesCount = 0;

    public ContestantModel() {
    }

    public ContestantModel(String electionId, String contestantName, String userId){

        this.electionId = electionId;
        this.contestantName = contestantName;
        this.userId = userId;
    }

    public String get_id() {
        return _id;
    }

    public String getElectionId() {
        return electionId;
    }

    public String getContestantId() {
        return contestantId;
    }

    public String getContestantName() {
        return contestantName;
    }

    public String getContestantEmail() {
        return contestantEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getOrganization() {
        return organization;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getVotes() {
        return votes;
    }

    public Number getVotesCount() {
        return votesCount;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public void setContestantId(String contestantId) {
        this.contestantId = contestantId;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    public void setContestantEmail(String contestantEmail) {
        this.contestantEmail = contestantEmail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    public void setVotesCount(Number votesCount) {
        this.votesCount = votesCount;
    }

    
}
