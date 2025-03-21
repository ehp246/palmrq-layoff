package com.palmrq.layoff.artingest.article.kafka.outbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("FailedIngestion")
@RequiredArgsConstructor
public class OnFailedIngestion {
    private static final Logger LOGGER = LogManager.getLogger();

    public void invoke(@OfValue FailedIngestionPayload payload) {
        LOGGER.debug(payload);
    }
}
