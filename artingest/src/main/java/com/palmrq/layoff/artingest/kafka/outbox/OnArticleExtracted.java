package com.palmrq.layoff.artingest.kafka.outbox;

import java.time.Clock;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;

import com.palmrq.layoff.artingest.kafka.ArticleOutbox.ArticleExtractedPayload;
import com.palmrq.layoff.artingest.model.LayoffEvent;
import com.palmrq.layoff.artingest.model.LayoffEvent.Source;
import com.palmrq.layoff.artingest.model.LayoffEvent.SourceType;
import com.palmrq.layoff.artingest.model.repository.LayoffEventRepository;
import com.palmrq.layoff.artingest.model.repository.MonthlyBriefRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("ArticleExtracted")
@RequiredArgsConstructor
@Log4j2
public class OnArticleExtracted {
    @Qualifier("systemDefaultZone")
    private final Clock clockSystemDefaultZone;
    private final LayoffEventRepository recordRepo;
    private final MonthlyBriefRepository briefRepository;

    public void invoke(@OfValue ArticleExtractedPayload payload) {
        final var extracted = payload.extracted();

        if (extracted.number() == null || extracted.number() <= 0) {
            LOGGER.atWarn().log("Invalid id:{}, number:{}", payload::id, extracted::number);
        }

        this.recordRepo.save(LayoffEvent.builder().id(payload.id()).company(extracted.company())
                .number(extracted.number()).location(extracted.location()).date(extracted.date())
                .percentage(extracted.percentage()).position(extracted.position()).reason(extracted.reason())
                .source(new Source(SourceType.Web, Map.of("url", payload.article().url()))).build());

        final var yearMonth = YearMonth
                .from(Optional.ofNullable(extracted.date()).or(() -> Optional.ofNullable(payload.article().date()))
                        .orElseGet(() -> LocalDate.now(this.clockSystemDefaultZone)));

        this.briefRepository.upsertRecordAndNumber(yearMonth, extracted.number());

        LOGGER.atTrace().log("{} updated by id:{}, number:{} ", () -> yearMonth, payload::id, extracted::number);
    }
}
