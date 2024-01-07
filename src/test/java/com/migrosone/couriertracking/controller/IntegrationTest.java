package com.migrosone.couriertracking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class IntegrationTest {

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
