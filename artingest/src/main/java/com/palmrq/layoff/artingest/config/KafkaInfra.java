package com.palmrq.layoff.artingest.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.ehp246.aufkafka.api.consumer.ConsumerConfigProvider;
import me.ehp246.aufkafka.api.producer.ProducerConfigProvider;

@Configuration
public class KafkaInfra {
    @Bean
    ConsumerConfigProvider consumerConfigProvider(final KafkaConfig config) {
        final Map<String, Object> map = Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootStrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, config.groupId());
        return name -> map;
    }

    @Bean
    ProducerConfigProvider producerConfigProvider(final KafkaConfig config) {
        final Map<String, Object> map = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootStrapServers());
        return name -> map;
    }
}
