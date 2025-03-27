package com.palmrq.layoff.artingest.kafka;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;

import com.palmrq.layoff.artingest.model.Article;
import com.palmrq.layoff.artingest.model.LlmExtracted;

import me.ehp246.aufkafka.api.annotation.ByKafka;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ByKafka("${app.kafka.topic.article.outbox}")
public interface ArticleOutbox {
    void articleExtracted(@OfValue ArticleExtractedPayload payload);

    void failedExtraction(@OfValue FailedExtractionPayload payload);

    public record ArticleExtractedPayload(String id, Article article, LlmExtracted extracted, Instant timestamp) {
        public YearMonth yearMonth() {
            if (extracted.date() != null) {
                return YearMonth.from(extracted.date().atZone(ZoneOffset.UTC));
            }
            if (article.timestamp() != null) {
                return YearMonth.from(article.timestamp().atZone(ZoneOffset.UTC));
            }
            return YearMonth.from(this.timestamp.atZone(ZoneOffset.UTC));
        }
    }

    public record FailedExtractionPayload(String id, Article article, String message) {
    }
}
