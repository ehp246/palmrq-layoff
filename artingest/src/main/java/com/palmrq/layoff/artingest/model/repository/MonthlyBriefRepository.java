package com.palmrq.layoff.artingest.model.repository;

import java.time.YearMonth;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

import com.palmrq.layoff.artingest.model.MonthlyBrief;

public interface MonthlyBriefRepository extends MongoRepository<MonthlyBrief, YearMonth> {
    @Update("{ $push: { records: ?1 }, $inc: { number: ?2 }, $set: { updatedAt: new Date() } }")
    @Query("{ _id: ?0 }")
    long updateExisting(YearMonth yearMonth, String newRecordId, int newNumber);

    @Transactional
    default void upsertRecordAndNumber(YearMonth yearMonth, String newRecordId, int newNumber) {
        if (!existsById(yearMonth)) {
            save(MonthlyBrief.builder().yearMonth(yearMonth).records(Set.of(newRecordId)).number(newNumber).build());
        } else {
            updateExisting(yearMonth, newRecordId, newNumber);
        }
    }
}
