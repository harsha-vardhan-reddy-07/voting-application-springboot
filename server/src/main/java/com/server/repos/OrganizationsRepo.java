package com.server.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.models.OrganizationsModel;

public interface OrganizationsRepo extends MongoRepository<OrganizationsModel, String>{

        OrganizationsModel findByEmail(String email);
} 