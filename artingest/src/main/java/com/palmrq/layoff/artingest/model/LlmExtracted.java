package com.palmrq.layoff.artingest.model;

import java.time.Instant;

public record LlmExtracted(String company, Integer number, Double percentage, String location, Instant date,
        String position, String reason) {
}