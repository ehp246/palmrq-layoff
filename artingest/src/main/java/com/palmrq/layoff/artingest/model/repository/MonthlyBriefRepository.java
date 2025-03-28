package com.palmrq.layoff.artingest.model.repository;

import java.time.YearMonth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

import com.palmrq.layoff.artingest.model.MonthlyBrief;

public interface MonthlyBriefRepository extends MongoRepository<MonthlyBrief, YearMonth> {
    @Update("{ $inc: { number: ?1, incidenceCount: 1 }, $set: { updatedAt: new Date() } }")
    @Query("{ _id: ?0 }")
    long updateExisting(YearMonth yearMonth, int newNumber);

    @Transactional
    default void upsertRecordAndNumber(YearMonth yearMonth, int newNumber) {
        if (!existsById(yearMonth)) {
            save(MonthlyBrief.builder().yearMonth(yearMonth).number(newNumber).incidenceCount(1).build());
        } else {
            updateExisting(yearMonth, newNumber);
        }
    }
}
