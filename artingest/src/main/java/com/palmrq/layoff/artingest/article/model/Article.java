package com.palmrq.layoff.artingest.article.model;

import java.time.Instant;

public record Article(String title, String content, String url, Instant date) {
}