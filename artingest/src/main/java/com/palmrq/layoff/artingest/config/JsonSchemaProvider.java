package com.palmrq.layoff.artingest.config;

import com.fasterxml.jackson.databind.node.ObjectNode;

@FunctionalInterface
public interface JsonSchemaProvider {
    ObjectNode get(Class<?> type);
}
