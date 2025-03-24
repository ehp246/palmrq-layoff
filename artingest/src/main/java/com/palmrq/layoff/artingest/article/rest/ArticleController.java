package com.palmrq.layoff.artingest.article.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmrq.layoff.artingest.article.model.Article;
import com.palmrq.layoff.artingest.article.rest.service.SubmitArticle;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/ingestion/article")
@RequiredArgsConstructor
public class ArticleController {
    private final SubmitArticle submitArticle;

    @PostMapping
    Map<String, String> newArticle(@RequestBody Article article) {
        return Map.of("id", this.submitArticle.apply(article));
    }
}
