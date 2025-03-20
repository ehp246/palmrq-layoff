package com.palmrq.layoff.artingest.article.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("NewArticle")
public class OnNewActicle {
    private static final Logger LOGGER = LogManager.getLogger();

    public void invoke(@OfValue ArctileInboxPayload payload) {
        LOGGER.debug(payload);
    }
}
