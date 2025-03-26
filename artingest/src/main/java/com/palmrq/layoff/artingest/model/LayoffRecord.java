package com.palmrq.layoff.artingest.model;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Document(collection = "layoff-records")
@Accessors(fluent = true)
@Getter
@Builder
@CompoundIndex(def = "{'company': 1, 'date': 1}")
public class LayoffRecord {
    @Id
    private final String id;
    @Indexed
    private final String company;
    private final int number;
    /**
     * When it happened.
     */
    @Indexed
    private final Instant date;
    private final Source source;

    private Double percentage;
    private String location;
    @Indexed
    private String position;
    private String reason;
    private boolean verified;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public static record Source(String id, SourceType type, Map<String, Object> clientMeta) {
    }

    public static enum SourceType {
        Web, Individual
    }
}
