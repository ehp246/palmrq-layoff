package com.palmrq.layoff.artingest.article.kafka;

import me.ehp246.aufkafka.api.annotation.ByKafka;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ByKafka("${app.kafka.topic.article.inbox}")
public interface ArticleInbox {
    void newArticle(@OfValue InboxPayload payload);
}
