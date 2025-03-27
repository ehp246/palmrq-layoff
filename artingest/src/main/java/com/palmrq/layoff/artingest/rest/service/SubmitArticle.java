package com.palmrq.layoff.artingest.rest.service;

import com.palmrq.layoff.artingest.model.Article;

@FunctionalInterface
public interface SubmitArticle {
    String apply(Article article);
}
