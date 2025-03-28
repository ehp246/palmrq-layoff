package com.palmrq.layoff.artingest.kafka.outbox;

import java.util.Map;

import com.palmrq.layoff.artingest.kafka.ArticleOutbox.ArticleExtractedPayload;
import com.palmrq.layoff.artingest.model.LayoffRecord;
import com.palmrq.layoff.artingest.model.LayoffRecord.Source;
import com.palmrq.layoff.artingest.model.LayoffRecord.SourceType;
import com.palmrq.layoff.artingest.model.repository.LayoffRecordsRepository;
import com.palmrq.layoff.artingest.model.repository.MonthlyBriefRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("ArticleExtracted")
@RequiredArgsConstructor
@Log4j2
public class OnArticleExtracted {

    private final LayoffRecordsRepository recordRepo;
    private final MonthlyBriefRepository briefRepository;

    public void invoke(@OfValue ArticleExtractedPayload payload) {
        final var extracted = payload.extracted();

        if (extracted.number() == null || extracted.number() <= 0) {
            LOGGER.atWarn().log("Invalid id:{}, number:{}", payload::id, extracted::number);
        }

        this.recordRepo.save(LayoffRecord.builder().id(payload.id()).company(extracted.company())
                .number(extracted.number()).location(extracted.location()).date(extracted.date())
                .percentage(extracted.percentage()).position(extracted.position()).reason(extracted.reason())
                .source(new Source(SourceType.Web, Map.of("url", payload.article().url()))).build());

        this.briefRepository.upsertRecordAndNumber(payload.yearMonth(), payload.id(), extracted.number());

        LOGGER.atTrace().log("{} updated by id:{}, number:{} ", payload::yearMonth, payload::id, extracted::number);
    }
}
