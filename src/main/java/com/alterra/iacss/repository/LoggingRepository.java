package com.alterra.iacss.repository;

import com.alterra.iacss.domain.dao.LoggingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<LoggingModel, String> {
}
