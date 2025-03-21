package com.palmrq.layoff.artingest.article.kafka;

import com.palmrq.layoff.artingest.article.kafka.outbox.FailedIngestionPayload;
import com.palmrq.layoff.artingest.article.kafka.outbox.NewIngestionPayload;

import me.ehp246.aufkafka.api.annotation.ByKafka;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ByKafka("${app.kafka.topic.article.outbox}")
public interface ArticleOutbox {
    void newIngestion(@OfValue NewIngestionPayload payload);

    void failedIngestion(@OfValue FailedIngestionPayload payload);
}
