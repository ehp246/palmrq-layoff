package com.palmrq.layoff.artingest.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.ehp246.aufkafka.api.consumer.ConsumerConfigProvider;

@Configuration
public class KafkaBeans {
    @Bean
    ConsumerConfigProvider consumerConfigProvider(final KafkaConfig config) {
        final Map<String, Object> map = Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootStrapServers());
        return name -> map;
    }
}
