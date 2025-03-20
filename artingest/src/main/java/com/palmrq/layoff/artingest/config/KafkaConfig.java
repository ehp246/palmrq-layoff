package com.palmrq.layoff.artingest.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka")
public record KafkaConfig(List<String> bootStrapServers, String articleInbox, String groupId) {
}
