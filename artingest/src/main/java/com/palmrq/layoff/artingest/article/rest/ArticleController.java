package com.palmrq.layoff.artingest.article.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmrq.layoff.artingest.article.kafka.InboxPayload;
import com.palmrq.layoff.artingest.article.kafka.ArticleInbox;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/ingestion/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleInbox inbox;

    @PostMapping
    void newArticle(@RequestBody InboxPayload payload) {
        this.inbox.newArticle(payload);
    }
}
