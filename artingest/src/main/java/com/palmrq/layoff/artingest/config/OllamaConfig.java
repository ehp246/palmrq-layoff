package com.palmrq.layoff.artingest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.ollama")
public record OllamaConfig(String model, String apiBase) {
}
