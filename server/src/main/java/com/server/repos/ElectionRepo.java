package com.server.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.models.ElectionModel;

public interface ElectionRepo extends MongoRepository<ElectionModel, String>{

    
}
