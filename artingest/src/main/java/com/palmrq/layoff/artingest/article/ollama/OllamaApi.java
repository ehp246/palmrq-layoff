package com.palmrq.layoff.artingest.article.ollama;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import me.ehp246.aufrest.api.annotation.ByRest;
import me.ehp246.aufrest.api.annotation.OfRequest;

@ByRest("${app.ollama.api-base}")
public interface OllamaApi {

    @OfRequest("/generate")
    Response postGenerate(Request req);

    record Request(String model, JsonNode format, String prompt) {
        @JsonProperty
        public boolean stream() {
            return false;
        }
    }

    record Response(String model, String response, Instant createdAt) {
    }
}
