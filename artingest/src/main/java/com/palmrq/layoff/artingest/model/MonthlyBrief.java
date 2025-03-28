package com.palmrq.layoff.artingest.model;

import java.time.Instant;
import java.time.YearMonth;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Document(collection = "monthly-brief")
@Accessors(fluent = true)
@Getter
@Builder
public class MonthlyBrief {
    /**
     * This value should always be in UTC.
     */
    @Id
    private final YearMonth yearMonth;
    /**
     * Total number of laid-off employees
     */
    private final long number;
    /**
     * Total number of lay-off incidences
     */
    private final long incidenceCount;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    private long version;
}
