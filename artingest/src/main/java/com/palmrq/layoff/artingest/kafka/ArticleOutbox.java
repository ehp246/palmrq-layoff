package com.palmrq.layoff.artingest.kafka;

import com.palmrq.layoff.artingest.model.Article;
import com.palmrq.layoff.artingest.model.LlmExtracted;

import me.ehp246.aufkafka.api.annotation.ByKafka;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ByKafka("${app.kafka.topic.article.outbox}")
public interface ArticleOutbox {
    void articleExtracted(@OfValue ArticleExtractedPayload payload);

    void failedExtraction(@OfValue FailedExtractionPayload payload);

    public record ArticleExtractedPayload(String id, Article article, LlmExtracted extracted) {
    }

    public record FailedExtractionPayload(String id, Article article, String message) {
    }
}
