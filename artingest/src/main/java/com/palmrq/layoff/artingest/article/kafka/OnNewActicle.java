package com.palmrq.layoff.artingest.article.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.palmrq.layoff.artingest.article.ollama.OllamaApi;
import com.palmrq.layoff.artingest.article.ollama.OllamaApi.Request;
import com.palmrq.layoff.artingest.config.OllamaConfig;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("NewArticle")
@RequiredArgsConstructor
public class OnNewActicle {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ObjectMapper objectMapper;
    private final SchemaGenerator schemaGenerator;
    private final OllamaConfig ollamaConfig;
    private final OllamaApi ollamaApi;

    public void invoke(@OfValue ArctileInboxPayload inBoxPayload) throws JsonMappingException, JsonProcessingException {
        final var response = ollamaApi.postGenerate(new Request(ollamaConfig.model(),
                schemaGenerator.generateSchema(OllamaApi.Response.Payload.class), inBoxPayload.content()));

        final var payload = this.objectMapper.readValue(response.response(), OllamaApi.Response.Payload.class);

        LOGGER.debug(payload);
    }
}
