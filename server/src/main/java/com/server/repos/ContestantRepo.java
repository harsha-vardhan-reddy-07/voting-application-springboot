package com.server.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.models.ContestantModel;

public interface ContestantRepo extends MongoRepository<ContestantModel, String>{

    List<ContestantModel> findAllByElectionId(String electionId);
    ContestantModel findByUserId(String userId);
}