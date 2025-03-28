package com.palmrq.layoff.artingest.kafka.inbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palmrq.layoff.artingest.config.JsonSchemaProvider;
import com.palmrq.layoff.artingest.config.OllamaConfig;
import com.palmrq.layoff.artingest.kafka.ArticleInbox.ArticleSubmittedPayload;
import com.palmrq.layoff.artingest.kafka.ArticleOutbox;
import com.palmrq.layoff.artingest.model.LlmExtracted;
import com.palmrq.layoff.artingest.ollama.OllamaApi;
import com.palmrq.layoff.artingest.ollama.OllamaApi.Request;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.ehp246.aufkafka.api.annotation.ForKey;
import me.ehp246.aufkafka.api.annotation.OfValue;

@ForKey("ArticleSubmitted")
@RequiredArgsConstructor
@Log4j2
public class OnActicleSubmitted {

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

        if (extracted.company() == null || extracted.company().isBlank() || extracted.number() == null
                || extracted.number() <= 0) {
            throw new IllegalArgumentException("Company and number must be specified");
        }

        this.articleOutbox.articleExtracted(
                new ArticleOutbox.ArticleExtractedPayload(newSubmission.id(), newSubmission.article(), extracted));
    }
}
