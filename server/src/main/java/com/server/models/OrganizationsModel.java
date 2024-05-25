package com.server.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "organizations")
public class OrganizationsModel {
    
    public String _id;
    public String username;
    public String password;
    public String email;
    public String role;
    public List<String> elections = new ArrayList<String>();
    public Number electionsCount = 0;

    public OrganizationsModel() {
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<String> getElections() {
        return elections;
    }

    public Number getElectionsCount() {
        return electionsCount;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setElections(List<String> elections) {
        this.elections = elections;
    }

    public void setElectionsCount(Number electionsCount) {
        this.electionsCount = electionsCount;
    }
    
}
