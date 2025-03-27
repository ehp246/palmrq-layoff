package com.palmrq.layoff.artingest.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Document(collection = "failed-extractions")
@Accessors(fluent = true)
@Getter
@Builder
public class FailedExtraction {
    @Id
    private final String id;
    private final Article article;
    private final String message;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

}
