package com.server.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "election")
public class ElectionModel {
    
    public String _id;
    public String title;
    public String description;
    public String start;
    public String end;
    public String status = "live";
    public String organization;
    public String organizationId;
    public List<String> appliedCandidates = new ArrayList<String>();
    public List<String> voters = new ArrayList<String>();
    public List<ContestantModel> candidates = new ArrayList<ContestantModel>();

    public ElectionModel() {
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getStatus() {
        return status;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public List<String> getAppliedCandidates() {
        return appliedCandidates;
    }

    public List<String> getVoters() {
        return voters;
    }

    public List<ContestantModel> getCandidates() {
        return candidates;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setAppliedCandidates(List<String> appliedCandidates) {
        this.appliedCandidates = appliedCandidates;
    }

    public void setVoters(List<String> voters) {
        this.voters = voters;
    }

    public void setCandidates(List<ContestantModel> candidates) {
        this.candidates = candidates;
    }

    
}
