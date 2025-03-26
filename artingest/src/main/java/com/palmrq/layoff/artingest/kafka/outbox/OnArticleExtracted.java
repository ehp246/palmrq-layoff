package com.palmrq.layoff.artingest.kafka.outbox;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.palmrq.layoff.artingest.kafka.ArticleOutbox.ArticleExtractedPayload;
import com.palmrq.layoff.artingest.model.LayoffRecord;
import com.palmrq.layoff.artingest.model.LayoffRecord.Source;
import com.palmrq.layoff.artingest.model.LayoffRecord.SourceType;
import com.palmrq.layoff.artingest.model.repository.LayoffRecordsRepository;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("ArticleExtracted")
@RequiredArgsConstructor
public class OnArticleExtracted {
    private static final Logger LOGGER = LogManager.getLogger();

    private final LayoffRecordsRepository repo;

    public void invoke(@OfValue ArticleExtractedPayload payload) {
        final var extracted = payload.extracted();

        repo.save(LayoffRecord.builder().id(payload.id()).company(extracted.company()).number(extracted.number())
                .location(extracted.location()).date(extracted.date()).percentage(extracted.percentage())
                .position(extracted.position()).reason(extracted.reason())
                .source(new Source(SourceType.Web, Map.of("url", payload.article().url()))).build());
    }
}
