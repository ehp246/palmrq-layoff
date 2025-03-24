package com.palmrq.layoff.artingest.article.rest.service;

import com.palmrq.layoff.artingest.article.model.Article;

@FunctionalInterface
public interface SubmitArticle {
    String apply(Article article);
}
