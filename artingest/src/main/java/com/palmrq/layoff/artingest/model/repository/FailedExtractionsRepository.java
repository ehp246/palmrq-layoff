package com.palmrq.layoff.artingest.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.palmrq.layoff.artingest.model.FailedExtraction;

public interface FailedExtractionsRepository extends MongoRepository<FailedExtraction, String> {

}
