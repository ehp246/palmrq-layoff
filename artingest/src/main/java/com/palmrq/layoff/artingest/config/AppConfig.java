package com.palmrq.layoff.artingest.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

@Configuration
public class AppConfig {
    @Bean
    JsonSchemaProvider jsonSchemaProvider() {
        final var builder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON)
                .with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT).without(Option.SCHEMA_VERSION_INDICATOR);

        builder.forFields().withRequiredCheck(field -> true);

        final var generator = new SchemaGenerator(builder.build());
        final var map = new ConcurrentHashMap<Class<?>, ObjectNode>();

        return type -> map.computeIfAbsent(type, key -> generator.generateSchema(key));
    }
}
