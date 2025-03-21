package com.palmrq.layoff.artingest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

@Configuration
public class AppConfig {
    @Bean
    SchemaGenerator schemaGenerator() {
        final var builder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON)
                .with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT).without(Option.SCHEMA_VERSION_INDICATOR);

        builder.forFields().withRequiredCheck(field -> true);

        return new SchemaGenerator(builder.build());
    }
}
