package com.palmrq.layoff.artingest.model;

import java.time.LocalDate;

public record Article(String title, String content, String url, LocalDate date) {
}