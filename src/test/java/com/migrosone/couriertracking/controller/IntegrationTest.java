package com.migrosone.couriertracking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract Integration Test class to keep common methods.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

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
