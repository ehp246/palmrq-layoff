package com.palmrq.layoff.artingest.article.kafka.outbox;

import com.palmrq.layoff.artingest.article.kafka.InboxPayload;
import com.palmrq.layoff.artingest.article.ollama.LlmExtracted;

public record NewIngestionPayload(InboxPayload inboxPayload, LlmExtracted extracted) {
}