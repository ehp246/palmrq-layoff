package com.palmrq.layoff.artingest.article.kafka.outbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.palmrq.layoff.artingest.article.kafka.ArticleOutbox.FailedExtractionPayload;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("FailedExtraction")
@RequiredArgsConstructor
public class OnFailedExtraction {
    private static final Logger LOGGER = LogManager.getLogger();

    public void invoke(@OfValue FailedExtractionPayload payload) {
        LOGGER.debug(payload);
    }
}
