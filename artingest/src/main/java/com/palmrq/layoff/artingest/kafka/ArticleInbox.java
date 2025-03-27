package com.palmrq.layoff.artingest.kafka;

import com.palmrq.layoff.artingest.model.Article;

import me.ehp246.aufkafka.api.annotation.ByKafka;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ByKafka("${app.kafka.topic.article.inbox}")
public interface ArticleInbox {
    void articleSubmitted(@OfValue ArticleSubmittedPayload value);

    public record ArticleSubmittedPayload(String id, Article article) {
    }
}
