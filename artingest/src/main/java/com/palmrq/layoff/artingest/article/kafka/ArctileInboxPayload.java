package com.palmrq.layoff.artingest.article.kafka;

import java.time.Instant;

public record ArctileInboxPayload(String title, String content, String url, Instant date) {

}
