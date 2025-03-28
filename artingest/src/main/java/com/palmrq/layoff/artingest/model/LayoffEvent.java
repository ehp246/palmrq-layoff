package com.palmrq.layoff.artingest.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Document(collection = "layoff-event")
@Accessors(fluent = true)
@Getter
@Builder
@CompoundIndex(def = "{'company': 1, 'date': 1}")
public class LayoffEvent {
    @Id
    private final String id;
    @Indexed
    @NonNull
    private final String company;
    @NonNull
    private final int number;
    /**
     * When it happened according to the original article.
     */
    @Indexed
    @Nullable
    private final LocalDate date;
    private Double percentage;
    private String location;
    @Indexed
    private String position;
    private String reason;

    private final Source source;

    @CreatedDate
    private final Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public static record Source(SourceType type, Map<String, Object> clientMeta) {
    }

    public static enum SourceType {
        Web, Individual
    }
}
