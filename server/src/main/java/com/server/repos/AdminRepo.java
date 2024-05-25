package com.server.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.models.AdminModel;

public interface AdminRepo extends MongoRepository<AdminModel, String>{

    AdminModel findByEmail(String email);
}