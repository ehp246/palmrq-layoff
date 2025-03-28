package com.palmrq.layoff.artingest.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.palmrq.layoff.artingest.model.LayoffEvent;

public interface LayoffEventRepository extends MongoRepository<LayoffEvent, String> {
    @Query("{ 'company' : { $regex: ?0, $options: 'i' } }")
    List<LayoffEvent> findByCompanyLike(String namePattern);
}
