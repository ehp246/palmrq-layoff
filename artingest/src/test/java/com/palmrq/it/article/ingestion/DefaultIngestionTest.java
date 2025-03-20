package com.palmrq.it.article.ingestion;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.palmrq.it.infra.TestInfra;
import com.palmrq.layoff.artingest.article.kafka.ArctileInboxPayload;
import com.palmrq.layoff.artingest.article.kafka.ArticleInbox;
import com.palmrq.layoff.artingest.config.KafkaConfig;
import com.palmrq.layoff.artingest.config.KafkaInfra;

@SpringBootTest(classes = { TestInfra.class, KafkaConfig.class,
        KafkaInfra.class }, webEnvironment = WebEnvironment.NONE)
public class DefaultIngestionTest {
    @Autowired
    private ArticleInbox inbox;

    @Test
    void sendArticle() {
        inbox.newArticle(new ArctileInboxPayload(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), Instant.now()));
    }
}
