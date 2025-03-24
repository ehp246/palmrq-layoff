package com.palmrq.layoff.artingest.article.kafka.inbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palmrq.layoff.artingest.article.kafka.ArticleOutbox;
import com.palmrq.layoff.artingest.article.kafka.ArticleInbox.ArticleSubmittedPayload;
import com.palmrq.layoff.artingest.article.model.LlmExtracted;
import com.palmrq.layoff.artingest.article.ollama.OllamaApi;
import com.palmrq.layoff.artingest.article.ollama.OllamaApi.Request;
import com.palmrq.layoff.artingest.config.JsonSchemaProvider;
import com.palmrq.layoff.artingest.config.OllamaConfig;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("ArticleSubmitted")
@RequiredArgsConstructor
public class OnActicleSubmitted {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ObjectMapper objectMapper;
    private final JsonSchemaProvider jsonSchemaProvider;
    private final OllamaConfig ollamaConfig;
    private final OllamaApi ollamaApi;
    private final ArticleOutbox articleOutbox;

    public void invoke(@OfValue ArticleSubmittedPayload newSubmission)
            throws JsonMappingException, JsonProcessingException {
        final var response = ollamaApi
                .postGenerate(new Request(ollamaConfig.model(), jsonSchemaProvider.get(LlmExtracted.class),
                        ollamaConfig.promptInstruction() + newSubmission.article().content()));

        final var extracted = this.objectMapper.readValue(response.response(), LlmExtracted.class);

        this.articleOutbox.articleExtracted(
                new ArticleOutbox.ArticleExtractedPayload(newSubmission.id(), newSubmission.article(), extracted));
    }
}
