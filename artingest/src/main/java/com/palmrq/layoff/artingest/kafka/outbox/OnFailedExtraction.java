package com.palmrq.layoff.artingest.kafka.outbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.palmrq.layoff.artingest.kafka.ArticleOutbox.FailedExtractionPayload;
import com.palmrq.layoff.artingest.model.FailedExtraction;
import com.palmrq.layoff.artingest.model.repository.FailedExtractionRepository;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("FailedExtraction")
@RequiredArgsConstructor
public class OnFailedExtraction {
    private static final Logger LOGGER = LogManager.getLogger();

    private final FailedExtractionRepository repo;

    public void invoke(@OfValue FailedExtractionPayload payload) {
        this.repo.save(FailedExtraction.builder().id(payload.id()).article(payload.article()).message(payload.message())
                .build());
    }
}
