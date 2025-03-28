package com.palmrq.layoff.artingest.model;

import java.time.LocalDate;

public record LlmExtracted(String company, Integer number, Double percentage, String location, LocalDate date,
        String position, String reason) {
}