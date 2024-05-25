package com.server.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "users")
public class UsersModel {
    
    public String _id;
    public String username;
    public String password;
    public String email;
    public String role;
    public String organization;
    public String userId;

    public UsersModel() {
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

    public String getOrganization() {
        return organization;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    

}
