package com.palmrq.layoff.artingest.article.kafka.outbox;

import com.palmrq.layoff.artingest.article.kafka.InboxPayload;

public record FailedIngestionPayload(InboxPayload inboxPayload, String stackTrace) {

}
