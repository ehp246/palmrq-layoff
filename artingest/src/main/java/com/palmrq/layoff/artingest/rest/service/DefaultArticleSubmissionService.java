package com.palmrq.layoff.artingest.rest.service;

import java.time.Clock;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.palmrq.layoff.artingest.kafka.ArticleInbox;
import com.palmrq.layoff.artingest.kafka.ArticleInbox.ArticleSubmittedPayload;
import com.palmrq.layoff.artingest.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DefaultArticleSubmissionService implements SubmitArticle {
    private final Clock clock;
    private final ArticleInbox inbox;

    @Override
    public String apply(Article article) {
        final var payload = new ArticleSubmittedPayload(UUID.randomUUID().toString(), article, this.clock.instant());
        this.inbox.articleSubmitted(payload);
        return payload.id();
    }

}
