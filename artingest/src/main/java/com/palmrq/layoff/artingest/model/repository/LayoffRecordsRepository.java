package com.palmrq.layoff.artingest.model.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.palmrq.layoff.artingest.model.LayoffRecord;

public interface LayoffRecordsRepository extends MongoRepository<LayoffRecord, String> {
    List<LayoffRecord> findByDateBetween(Instant start, Instant end);

    @Query("{ 'company' : { $regex: ?0, $options: 'i' } }")
    List<LayoffRecord> findByCompanyLike(String namePattern);
}
